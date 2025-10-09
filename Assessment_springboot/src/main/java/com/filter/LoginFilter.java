package com.filter;

import com.entity.Result;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//访问除登录页面外的页面 必须先登录
public class LoginFilter implements Filter {
    //白名单
    private static final List<String> WHITE_LIST = Arrays.asList(
            "/api/auth/login",
            "/error"  //默认错误页
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();

        //白名单接口直接放行
        if (WHITE_LIST.contains(url)) {
            chain.doFilter(request, response);
            return;
        }

        //检查Session中是否有用户信息
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            // 未登录，返回401
            res.setContentType("application/json;charset=UTF-8");
            res.getWriter().write(new ObjectMapper().writeValueAsString(
                    Result.error("401", "请先登录")
            ));
            return;
        }
        chain.doFilter(request, response);
    }
}
