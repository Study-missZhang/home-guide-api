package com.guide.homeguideapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 应用基础配置类
 *
 * @author zky
 */
@Configuration
public class AppConfig {

    /**
     * 注册 RestTemplate Bean，用于发起 HTTP 请求
     * WechatUtil 通过此 Bean 调用微信接口
     *
     * @return RestTemplate 实例
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
