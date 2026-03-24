package com.guide.homeguideapi.context;

/**
 * 用户上下文，基于 ThreadLocal 存储当前请求的用户ID
 * 每个请求线程独立，互不干扰
 *
 * @author zky
 */
public class UserContext {

    private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();

    /**
     * 设置当前线程的用户ID（由拦截器调用）
     *
     * @param userId
     */
    public static void setUserId(Long userId){
        USER_ID.set(userId);
    }

    /**
     * 获取当前线程的用户ID（业务代码中随时调用）
     * @return
     */
    public static Long getUserId(){
        return USER_ID.get();
    }

    /**
     * 清楚当前线程的用户ID（请求结束后必须调用，防止内存泄漏）
     */
    public static void clear(){
        USER_ID.remove();
    }
}
