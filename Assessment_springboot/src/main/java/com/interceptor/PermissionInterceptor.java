package com.interceptor;

import com.config.PermissionConfig;
import com.entity.Result;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

//权限验证 拦截器
@Component
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        String userRole = currentUser.getRole();

        //从权限映射中获取当前URL允许的角色
        List<String> allowedRoles = PermissionConfig.URL_PERMISSIONS.get(url);
        // 处理带占位符的URL（如/api/courses/123），简化匹配：取前缀
        if (allowedRoles == null) {
            for (Map.Entry<String, List<String>> entry : PermissionConfig.URL_PERMISSIONS.entrySet()) {
                String key = entry.getKey();
                if (url.contains(key)) {
                    allowedRoles = PermissionConfig.URL_PERMISSIONS.get(key);
                }
            }
        }
        //检查当前用户角色是否在允许范围内
        if (allowedRoles == null || !allowedRoles.contains(userRole)) {
            response.setContentType("application/json;charset=UTF-8");
            // 权限不足，返回403
            response.getWriter().write(new ObjectMapper().writeValueAsString(
                    Result.error("403", "权限不足")
            ));
            return false;
        }

        return true;
    }

}
