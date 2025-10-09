package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

//-- 创建选课关联表
//CREATE TABLE `enrollment` (
//        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '选课记录ID',
//        `student_id` bigint NOT NULL COMMENT '学生ID(关联user表)',
//        `course_id` bigint NOT NULL COMMENT '课程ID(关联course表)',
//        `enrolled_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '选课时间',
//          PRIMARY KEY (`id`),
//          UNIQUE KEY `uk_student_course` (`student_id`,`course_id`),
//          KEY `fk_enroll_student` (`student_id`),
//          KEY `fk_enroll_course` (`course_id`),
//          CONSTRAINT `fk_enroll_student` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`),
//          CONSTRAINT `fk_enroll_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
//        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选课关联表';
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    private Long id;
    private Long student_id;
    private Long course_id;
    private Date enrolled_time;


}
