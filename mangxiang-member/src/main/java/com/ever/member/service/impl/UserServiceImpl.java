package com.ever.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ever.member.config.security.JwtTokenUtil;
import com.ever.member.entity.RespBean;
import com.ever.member.entity.User;
import com.ever.member.mapper.UserMapper;
import com.ever.member.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Everxys
 * @since 2021-02-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    //登录
    @Autowired
    private UserDetailsService userDetailsService;
    //加密密码
    @Autowired
    private PasswordEncoder passwordEncoder;
    //生成token
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    //通过@Value注解拿到tokenHead头部信息
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * 登录,登录之后返回token
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        //获取验证码
        String captcha=(String)request.getSession().getAttribute("captcha");
        //验证验证码是否为空,忽略大小写
        if(StringUtils.isEmpty(code)||!captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误,请重新输入!");
        }

        //通过用户名登录
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        //匹配加密后的密码
        if(null==userDetails||!passwordEncoder.matches(password,userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if(!userDetails.isEnabled()){
            return RespBean.error("账号被禁用,请联系管理员!");
        }


        /**
         * 更新security登录用户(密码(一般不放),权限列表)
         */
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                userDetails,null,userDetails.getAuthorities());
        //放在security的全局里面
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        /**
         * 登录成功,返回token
         */
        String token=jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap =new HashMap<>();
        //返回token的负载,签名以及头部
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登录成功",tokenMap);
    }
    /**
     * 根据用户名获取用户
     */
    @Override
    public User getUserByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("username",username)
                .eq("enabled",true));
    }
}
