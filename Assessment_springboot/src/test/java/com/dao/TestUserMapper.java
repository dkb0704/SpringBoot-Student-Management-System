package com.dao;

import com.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
@SpringBootTest
public class TestUserMapper {
    @Resource
    private UserMapper userMapper;
    @Test
    public void ListUser() {
        List<User> users = userMapper.ListUser();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }
    @Test
    public void searchUserById() {
        User user = userMapper.searchUserById(3L);
        System.out.println("user = " + user);
    }
    @Test
    public void queryUserByUsernameAndPassword() {
        User user = userMapper.queryUserByUsernameAndPassword("s001", "001");
        System.out.println("user = "+ user);
    }

}
