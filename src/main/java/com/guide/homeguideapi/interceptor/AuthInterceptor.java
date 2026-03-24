package com.guide.homeguideapi.interceptor;

import com.guide.homeguideapi.context.UserContext;
import com.guide.homeguideapi.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录认证拦截器
 * 校验请求头的 Token ， 解析 userId 存入 ThreadLocal
 * 请求结束自动清楚 ThreadLocal， 防止内存泄漏
 *
 * @author zky
 */
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    /**
     * 请求前，校验 token， 解析 userId 存入 UserContext
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        // 没有 token 或格式不对，返回401
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String token = authHeader.substring(7);

        // token 无效或过期则返回401
        if(!jwtUtil.validateToken(token)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 解析userId， 存入到 UserContext
        Long userId = jwtUtil.parseUserId(token);
        UserContext.setUserId(userId);
        return true;
    }

    /**
     * 请求完成后：清楚 ThreadLocal, 防止现成复用导致数据污染
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserContext.clear();
    }
}
