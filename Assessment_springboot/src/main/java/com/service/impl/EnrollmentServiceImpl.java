package com.service.impl;

import com.dao.CourseMapper;
import com.dao.EnrollmentMapper;
import com.dao.UserMapper;
import com.entity.Course;
import com.entity.Enrollment;
import com.entity.User;
import com.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Resource
    private EnrollmentMapper enrollmentMapper;
    @Resource
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public int deleteEnrollmentByCourseId(Long id) {
        return enrollmentMapper.deleteEnrollmentByCourseId(id);
    }

    @Override
    public List<User> searchStudentByCourseId(Long id) {
        List<Long> StudentIds = enrollmentMapper.searchStudentIdByCourseId(id);
        ArrayList<User> users = new ArrayList<>();
        for (Long StudentId : StudentIds) {
            User user = userMapper.searchUserById(StudentId);
            users.add(user);
        }
        return users;
    }

    @Override
    @Transactional
    public int insertEnrollment(Enrollment enrollment) {
        Course newCourse = new Course();
        Long courseId = enrollment.getCourse_id();
        Course course = courseMapper.searchCourseById(courseId);
        newCourse.setId(course.getId());
        newCourse.setCurrent_students(course.getCurrent_students()+1);
        courseMapper.updateCourse(newCourse);
        return enrollmentMapper.insertEnrollment(enrollment);
    }

    @Override
    @Transactional
    public int deleteEnrollment(Enrollment enrollment) {
        Course newCourse = new Course();
        Long courseId = enrollment.getCourse_id();
        Course course = courseMapper.searchCourseById(courseId);
        newCourse.setId(course.getId());
        newCourse.setCurrent_students(course.getCurrent_students()-1);
        courseMapper.updateCourse(newCourse);
        return enrollmentMapper.deleteEnrollment(enrollment);
    }

    @Override
    public List<Course> searchCourseByStudentId(Long id) {
        List<Long> courseIds = enrollmentMapper.searchCourseIdByStudentId(id);
        List<Course> courses = new ArrayList<>();
        for (Long courseId : courseIds) {
            Course course = courseMapper.searchCourseById(courseId);
            courses.add(course);
        }
        return courses;
    }
}
