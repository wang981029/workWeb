package com.wangfuzeng.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 王福增
 * @datetime 2023/8/5 15:27
 * @description 自定义拦配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 解决跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许所有方法的跨域
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST","GET");
    }



}
