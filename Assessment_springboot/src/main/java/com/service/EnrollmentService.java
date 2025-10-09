package com.service;

import com.entity.Course;
import com.entity.Enrollment;
import com.entity.User;

import java.util.List;

public interface EnrollmentService {
    //根据课程id删除所有选该课程的记录
    public int deleteEnrollmentByCourseId(Long id);
    //根据课程id返回选课学生信息
    public List<User> searchStudentByCourseId(Long id);
    //根据课程id选择课程
    public int insertEnrollment(Enrollment enrollment);
    //根据课程id 和学生id 退课
    public int deleteEnrollment(Enrollment enrollment);
    //根据学生id返回所选课程
    public List<Course> searchCourseByStudentId(Long id);
}
