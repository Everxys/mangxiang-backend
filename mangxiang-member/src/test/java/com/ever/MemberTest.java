package com.ever;


import com.ever.member.entity.User;
import com.ever.member.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
    @Test
    void isUsernameExist(){
        HashMap<String,Object> map= new HashMap<>();
        ArrayList list=new ArrayList();
        map.put("username","admin");
        List<User> result=userMapper.selectByMap(map);
        if (result.equals(list) ){
            System.out.println(false);
        }
        System.out.println(result);

    }

}
