package com.guide.homeguideapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.guide.homeguideapi.mapper")
@SpringBootApplication
public class HomeGuideApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeGuideApiApplication.class, args);
    }

}
