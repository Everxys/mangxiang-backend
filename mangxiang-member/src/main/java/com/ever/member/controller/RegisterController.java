package com.ever.member.controller;

import com.ever.member.entity.RespBean;
import com.ever.member.entity.UserLoginParam;
import com.ever.member.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册
 *
 * @AUTHOR: Everxys
 * @DATE: 2021/2/6 19:31
 **/
@Api(tags="RegisterController")
@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @ApiOperation(value="注册")
    //get请求,查看数据,put请求,更新数据,post请求,增加数据,delete请求,删除数据
    @PostMapping("/register")
    //RequestBody用来接受前端发送来的json字符串中的数据
    //前端发送的数据存在UserLoginParam里
    public RespBean register(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        //这个参数的意思就是用UserLoginParam的方法传入username,password,code,request
        return userService.register(userLoginParam.getUsername(),userLoginParam.getPassword(),userLoginParam.getCode(),request);
    }
}
