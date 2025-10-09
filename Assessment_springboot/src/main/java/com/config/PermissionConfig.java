package com.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionConfig {
    // 角色
    public static final String ROLE_STUDENT = "student";
    public static final String ROLE_TEACHER = "teacher";

    // 接口权限映射（URL -> 允许访问的角色列表）
    public static final Map<String, List<String>> URL_PERMISSIONS = new HashMap<>();
    static {
        // 学生接口：仅学生可访问
        URL_PERMISSIONS.put("/api/courses/available", Arrays.asList(ROLE_STUDENT));
        URL_PERMISSIONS.put("/api/enrollments", Arrays.asList(ROLE_STUDENT));
        URL_PERMISSIONS.put("/api/enrollments/student", Arrays.asList(ROLE_STUDENT));

        // 教师接口：仅教师可访问
        URL_PERMISSIONS.put("/api/users", Arrays.asList(ROLE_TEACHER));
        URL_PERMISSIONS.put("/api/courses", Arrays.asList(ROLE_TEACHER));

        // 公共接口（已登录即可访问）
        URL_PERMISSIONS.put("/api/users/profile", Arrays.asList(ROLE_STUDENT, ROLE_TEACHER));
    }

}

