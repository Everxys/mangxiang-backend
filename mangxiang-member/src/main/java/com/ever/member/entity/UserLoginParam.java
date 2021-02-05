package com.ever.member.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户登录实体类
 *
 * @Author: Everxys
 * @DATE: 2021/2/4 18:39
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain=true)
@ApiModel(value="UserLogin对象",description = "")
public class UserLoginParam {
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value="密码",required=true)
    private String password;
    @ApiModelProperty(value="验证码",required = true)
    private String code;
}
