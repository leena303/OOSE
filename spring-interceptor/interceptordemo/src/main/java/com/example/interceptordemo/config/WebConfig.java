package com.example.interceptordemo.config;

import com.example.interceptordemo.interceptor.AuthInterceptor;
import com.example.interceptordemo.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/**"); // log cho mọi request

        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/admin/**") // chỉ kiểm tra quyền với /admin/*
                .excludePathPatterns("/login", "/403", "/error"); // bỏ qua các trang này
    }
}


