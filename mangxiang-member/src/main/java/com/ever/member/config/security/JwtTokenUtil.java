package com.ever.member.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken工具类
 */

@Component
public class JwtTokenUtil {
    //准备两个常量,放在荷载里面,第一个是用户名的key,第二个是jwt创建时间,生成token用的,创建时间是因为token不能被解密
    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    //通过@Value注解到application.properties配置目录里面拿到jwt.secret密钥和jwt.expiration失效时间

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     */
    //通过security框架里的UserDetails拿取用户信息
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token){
        String username;
        try {
            //从token获取荷载
            Claims claims=getClaimsFromToken(token);
            //从荷载获取用户名
            username=claims.getSubject();
        } catch (Exception e) {
            username=null;
        }
        return username;
    }

    /**
     * 验证token是否有效 1.是否过期2.用户名是否一致
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username=getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     *判断token是否过期  通过当前时间和过期时间判断
     */
    private boolean isTokenExpired(String token) {
        Date expireDate=getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取负载,从负载中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims=getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否可以被刷新(如果过期了,重新获取token)
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     *刷新token(通过当前时间创建负载,通过负载创建token)
     */
    public String refreshToken(String token){
        Claims claims=getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     *通过密钥和token获取荷载
     */
    private Claims getClaimsFromToken(String token){
        Claims claims=null;
        try {
            //解析
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     *生成token方法,设置荷载(用户名,创建时间,失效时间),签名(HS512加密算法和密钥)
     */
    private  String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    /**
     * 生成token失效时间
     */
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

}