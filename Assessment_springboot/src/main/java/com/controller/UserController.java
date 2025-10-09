package com.controller;

import com.entity.Result;
import com.entity.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
public class UserController {
    @Resource
    private UserService userService;

    //    用户登录：POST /api/auth/login
    @PostMapping("/api/auth/login")
    @ResponseBody
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        User user = userService.queryUserByUsernameAndPassword(username, password);
        if (user == null) {
            return Result.error("401", "用户名或密码错误");
        } else {
            Result<User> result = Result.success("登录成功", user);
            session.setAttribute("currentUser", user);
            return result;
        }
    }

    //    查看个人信息：GET /api/users/profile
    @GetMapping("/api/users/profile")
    public Result searchById(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        User user_info = userService.searchUserById(user.getId());
        if (user_info == null) {
            return Result.error("404", "查看个人信息失败，请联系管理员");
        } else {
            return Result.success("查询成功", user_info);
        }
    }

    //    教师获取用户列表：GET /api/users
    @GetMapping("/api/users")
    public Result searchAllStudent() {
        List<User> users = userService.searchAllStudent();
        if (users == null) {
            return Result.error("404", "查看学生信息失败，请联系管理员");
        } else {
            return Result.success("查询成功", users);
        }
    }

    //教师创建用户：POST /api/users
    @PostMapping("/api/users")
    public Result insertUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("name") String name,
                             @RequestParam("role") String role,
                             @RequestParam("gender") String gender,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone
    ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setRole(role);
        user.setGender(gender);
        user.setEmail(email);
        user.setPhone(phone);
        int infected = userService.insertUser(user);
        if (infected == 0) {
            return Result.error("404", "创建学生信息失败，请联系管理员");
        } else {
            return Result.success("创建成功", user);
        }
    }
}
