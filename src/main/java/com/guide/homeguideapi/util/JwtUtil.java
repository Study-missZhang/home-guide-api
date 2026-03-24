package com.guide.homeguideapi.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT 工具类，负责 token 的生产和解析
 *
 * @author zky
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private int expire;

    /**
     * 生产JWT token
     * payload 中存储用户ID，设置签发时间和过期时间
     * @param userId
     * @return
     */
    public String generateToken(Long userId){
        return Jwts.builder()
                .setSubject(String.valueOf(userId))   // 存用户ID
                .setIssuedAt(new Date())              // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + (long) expire * 1000)) // 过期时间
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 从 token 中解析用户ID
     * token 无效或过期则抛出异常
     * @param token
     * @return
     */
    public Long parseUserId(String token){
        String subject = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return Long.valueOf(subject);
    }
}
