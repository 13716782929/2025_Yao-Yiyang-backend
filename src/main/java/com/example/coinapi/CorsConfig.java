package com.example.coinapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 允许所有路径
                        .allowedOrigins("http://localhost:3000") // 允许前端的地址
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
                        .allowedHeaders("*") // 允许的请求头
                        .allowCredentials(true); // 允许携带凭证（如 Cookie）
            }
        };
    }
}
