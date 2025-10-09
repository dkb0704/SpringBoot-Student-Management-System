package com.service;

import com.entity.Course;
import com.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {
    //返回可选择的课程
    public List<Course> listElectiveCourse();
    //返回全部课程
    public List<Course> listCourse();
    //创建课程
    public int insertCourse(Course course);
    //更新课程
    public int updateCourse(Course course);
    //根据id赶回课程
    public Course searchCourseById(Long id);
    //根据id删除课程
    public int deleteCourseById(Long id);
}
