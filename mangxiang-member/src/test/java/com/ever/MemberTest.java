package com.ever;


import com.ever.member.entity.User;
import com.ever.member.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MemberTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void addUser(){
        User user=new User();
        user.setUsername("test");
        user.setPassword("123456");
        userMapper.insert(user);
    }

}
