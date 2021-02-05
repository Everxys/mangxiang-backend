package com.ever.member.service;

import com.ever.member.entity.RespBean;
import com.ever.member.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Everxys
 * @since 2021-02-04
 */
public interface UserService extends IService<User> {
    //登录之后返回token
    RespBean login(String username, String password, String code, HttpServletRequest request);
    //根据用户名获取用户
    User getUserByUserName(String username);
}
