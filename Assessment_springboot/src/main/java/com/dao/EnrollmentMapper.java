package com.dao;

import com.entity.Enrollment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollmentMapper {
    //根据课程id删除所有选该课程的记录
    public int deleteEnrollmentByCourseId(Long id);
    //根据课程id返回选课学生
    public List<Long> searchStudentIdByCourseId(Long id);
    //根据课程id选课
    public int insertEnrollment(Enrollment enrollment);
    //根据课程id退课
    public int deleteEnrollment(Enrollment enrollment);
    //根据学生id返回课程id
    public List<Long> searchCourseIdByStudentId(Long id);
}
