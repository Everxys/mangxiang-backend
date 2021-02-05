package com.ever.member.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码
 *
 * @AUTHOR: Everxys
 * @DATE: 2021/2/5 12:54
 **/
@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value="验证码")
    //produces改造接口文档可以看图片
    @GetMapping(value="/captcha",produces="image/jpeg")
    //流的形式直接传图
    //response的响应头做处理
    public void captcha(HttpServletRequest request, HttpServletResponse response){
        response.setDateHeader("Expired",0);
        response.setHeader("Cache-Control","no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control","post-check=0,pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setContentType("image/jpeg");

        //生成验证码

        //获取验证码文本内容
        String text=defaultKaptcha.createText();
        System.out.println("验证码内容"+text);
        //将验证码文本内容放入session
        request.getSession().setAttribute("captcha",text);
        //根据验证码内容创建图形验证码
        BufferedImage image=defaultKaptcha.createImage(text);
        ServletOutputStream outputStream=null;
        try {
            outputStream=response.getOutputStream();
            //输出流输出图片,格式为jpg
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                if(null!=outputStream){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
