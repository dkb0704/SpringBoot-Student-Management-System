package com.service.impl;

import com.dao.CourseMapper;
import com.dao.EnrollmentMapper;
import com.dao.UserMapper;
import com.entity.Course;
import com.entity.User;
import com.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private EnrollmentMapper enrollmentMapper;

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        return userMapper.queryUserByUsernameAndPassword(username,password);
    }

    @Override
    public User searchUserById(Long id) {
        return userMapper.searchUserById(id);
    }

    @Override
    public List<User> searchAllStudent() {
        return userMapper.searchAllStudent();
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }


}
