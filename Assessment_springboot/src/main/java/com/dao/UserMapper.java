package com.dao;

import com.entity.Course;
import com.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //测试
    public List<User> ListUser();
    //根据用户名和密码查询用户
    public User queryUserByUsernameAndPassword(String username,String password);
    //查看个人信息
    public User searchUserById(Long id);
    //查看所有学生信息
    public List<User> searchAllStudent();
    //教师创建用户
    public int insertUser(User user);
}
