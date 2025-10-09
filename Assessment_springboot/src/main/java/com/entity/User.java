package com.entity;

//-- 创建用户表
//CREATE TABLE `user` (
//        `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
//        `username` VARCHAR(50) NOT NULL COMMENT '登录账号',
//        `password` VARCHAR(100) NOT NULL COMMENT '加密密码',
//        `name` VARCHAR(50) NOT NULL COMMENT '真实姓名',
//        `role` VARCHAR(20) NOT NULL COMMENT '角色(student/teacher)',
//        `gender` VARCHAR(10) DEFAULT NULL COMMENT '性别',
//        `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
//        `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
//        `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(1-启用/0-禁用)',
//        `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
//        `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
//         PRIMARY KEY (`id`),
//         UNIQUE KEY `uk_username` (`username`),
//         UNIQUE KEY `uk_email` (`email`)
//        ) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


//INSERT INTO `user` (`username`, `password`, `name`, `role`, `gender`, `email`,`phone`)
//VALUES
//        ('t001', MD5('001'), '王老师', 'teacher', '男', 'wang@teacher.com','10000'),
//        ('t002', MD5('002'), '李老师', 'teacher', '女', 'li@teacher.com','10001');
//
//INSERT INTO `user` (`username`, `password`, `name`, `role`, `gender`, `email`,`phone`)
//VALUES
//        ('s001', MD5('001'), '王同学', 'student', '男', 'wang@student.com','10003'),
//        ('s002', MD5('002'), '李同学', 'student', '女', 'li@student.com','10004');

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String role;
    private String gender;
    private String email;
    private String phone;
    private Integer status;
    private Date created_time;
    private Date updated_time;

}
