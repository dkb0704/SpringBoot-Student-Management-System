package com.service.impl;

import com.dao.CourseMapper;
import com.dao.EnrollmentMapper;
import com.dao.UserMapper;
import com.entity.Course;
import com.entity.User;
import com.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private EnrollmentMapper enrollmentMapper;
    @Override
    public List<Course> listElectiveCourse() {
        List<Course> courses = courseMapper.listCourse();
        List<Course> list = new ArrayList<>();
        for (Course course : courses) {
            if(course.getCurrent_students()<course.getMax_students()) {
                list.add(course);
            }
        }
        return list;
    }

    @Override
    public List<Course> listCourse() {
        return courseMapper.listCourse();
    }

    @Override
    public int insertCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public Course searchCourseById(Long id) {
        return courseMapper.searchCourseById(id);
    }

    @Override
    @Transactional
    public int deleteCourseById(Long id) {
        //先删除选择该课程的记录
        enrollmentMapper.deleteEnrollmentByCourseId(id);
        return courseMapper.deleteCourseById(id);
    }


}
