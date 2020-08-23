package com.xyh.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @Author SQ  生成与解析 token
 * @Date 2020/8/14 0014 12:49
 * @Version 1.0
 */
@Getter
@Setter
public class JwtUtil {

    private static final String key = "saas-xyh"; //签名私钥
    private static final long ttl = 360000; //签名失效时间


    /**
     * 签发 token
     * id : 登录用户id
     * subject: 登录用户名
     * Map : 指定的私有数据
     */
    public static String createJWT(String id, String subject, Map<String, Object> map) {
        //设置失效时间
        long now = System.currentTimeMillis(); //当前毫秒
        long exp = now + ttl;
        //创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setSubject(subject).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key);
        //根据map设置Claims
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }
        if (ttl > 0) {
            jwtBuilder.setExpiration(new Date(exp));
        }
        //生成token
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析JWTtoken 获取claims
     *
     * @param token
     * @return
     */
    public static Claims parseJWT(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
        }
        return claims;
    }
}

