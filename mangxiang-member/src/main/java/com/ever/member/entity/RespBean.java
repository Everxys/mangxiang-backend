package com.ever.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 *
 * @Author: Everxys
 * @DATE: 2021/2/4 17:51
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;
    //成功返回结果1
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }
    //成功返回结果2
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }
    //失败返回结果
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

}
