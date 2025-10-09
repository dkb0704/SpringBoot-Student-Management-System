package com.dao;

import com.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CourseMapper {
    //获取全部课程
    public List<Course> listCourse();
    //创建课程
    public int insertCourse(Course course);
    //更新课程信息
    public int updateCourse(Course course);
    //根据id获取课程
    public Course searchCourseById(Long id);
    //根据id删除课程
    public int deleteCourseById(Long id);


}
