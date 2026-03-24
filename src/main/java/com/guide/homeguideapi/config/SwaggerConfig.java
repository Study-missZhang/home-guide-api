package com.guide.homeguideapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger / OpenAPI 文档配置类
 *
 * @author zky
 */
@Configuration
public class SwaggerConfig {

    /**
     * 配置 OpenAPI 基础信息，展示在 Swagger UI 顶部
     *
     * @return OpenAPI 配置实例
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HomeGuide API")
                        .description("家的方向 - 后端接口文档")
                        .version("1.0.0"));
    }
}
