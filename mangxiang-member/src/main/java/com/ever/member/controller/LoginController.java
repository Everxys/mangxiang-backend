package com.ever.member.controller;

import com.ever.member.entity.RespBean;
import com.ever.member.entity.User;
import com.ever.member.entity.UserLoginParam;
import com.ever.member.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 登录
 *
 * @Author: Everxys
 * @DATE: 2021/2/4 18:41
 **/
@Api(tags="LoginController")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        //登录方法
        return userService.login(userLoginParam.getUsername(),userLoginParam.getPassword(),userLoginParam.getCode(),request);
    }

    @ApiOperation(value="获取当前登录用户信息")
    @GetMapping("/user/info")
    //principal获取当前登录对象
    public User getUserInfo(Principal principal){
        if(null==principal){
            return null;
        }
        String username=principal.getName();
        User user=userService.getUserByUserName(username);
        //不返回给前端密码
        user.setPassword(null);
        return user;
    }

    /**
     * 前端拿到200,就删除token
     */
    @ApiOperation(value="退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功!");
    }


}
