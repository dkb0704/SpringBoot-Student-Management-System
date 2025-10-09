package com.service;

import com.entity.Course;
import com.entity.User;

import java.util.List;

public interface UserService {
    //根据用户名和密码查询用户
    public User queryUserByUsernameAndPassword(String username,String password);
    //返回个人信息
    public User searchUserById(Long id);
    //查看所有学生信息
    public List<User> searchAllStudent();
    //教师创建用户
    public int insertUser(User user);

}
