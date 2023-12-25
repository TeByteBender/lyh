package com.example.homemanagementsystem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "itheima";
    private static Long expire = 43200000L; // 12小时

    /**
     * 生成 JWT 令牌
     * @param claims JWT 第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .addClaims(claims)  // 自定义内容（载荷）
                .signWith(SignatureAlgorithm.HS256, signKey)    // 签名算法
                .setExpiration(new Date(System.currentTimeMillis() + expire))   // 设置有效期 12h
                .compact();
        return jwt;
    }

    /**
     * 解析 JWT 令牌
     * @param jwt JWT 令牌
     * @return JWT 第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
