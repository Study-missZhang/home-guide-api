package com.guide.homeguideapi.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * JWT 工具类，基于 Hutool 实现 token 的生产和解析
 *
 * @author zky
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private long expire;

    /**
     * 生产JWT token
     * payload 中存储用户ID，设置签发时间和过期时间
     * @param userId
     * @return
     */
    public String generateToken(Long userId){
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userId);
        payload.put("exp", new Date(System.currentTimeMillis() + expire * 1000));
        return JWTUtil.createToken(payload, secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 验证 token 是否有效（签名正确且未过期）
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token){
        try {
            return JWTUtil.verify(token, secret.getBytes(StandardCharsets.UTF_8)) && !isExpired(token);

        } catch (Exception e){
            return false;
        }
    }

    /**
     * 从 token 中解析用户ID
     * token 无效或过期则抛出异常
     * @param token
     * @return
     */
    public Long parseUserId(String token){
        JWT jwt = JWTUtil.parseToken(token);
        return Long.valueOf(jwt.getPayload("userId").toString());
    }

    /**
     * 判断 token 是否过期
     * @param token
     * @return
     */
    private boolean isExpired(String token){
        JWT jwt = JWTUtil.parseToken(token);
        Object exp = jwt.getPayload("exp");
        if(exp == null) return true;
        // Hutool 解析出来的 exp 是 JSONObject，需要取 $numberLong 或直接转 long
        long expTime = Long.parseLong(exp.toString().replaceAll("[^0-9]]", ""));
        return expTime <= System.currentTimeMillis();
    }
}
