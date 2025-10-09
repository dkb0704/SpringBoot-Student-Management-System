package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

//-- 创建课程表
//CREATE TABLE `course` (
//        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '课程ID',
//        `name` varchar(100) NOT NULL COMMENT '课程名称',
//        `description` text COMMENT '课程描述',
//        `teacher_id` bigint NOT NULL COMMENT '授课教师ID(关联user表)',
//        `max_students` int NOT NULL COMMENT '最大选课人数',
//        `current_students` int NOT NULL DEFAULT 0 COMMENT '当前选课人数',
//        `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态(1-开放选课/0-关闭)',
//        `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
//        `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
//         PRIMARY KEY (`id`),
//         KEY `fk_teacher` (`teacher_id`),
//         CONSTRAINT `fk_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
//        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Long id;
    private String name;
    private String description;
    private Long teacher_id;
    private Integer max_students;
    private Integer current_students;
    private Integer status;
    private Date created_time;
    private Date updated_time;
}
