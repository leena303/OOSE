package com.example.interceptordemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME, startTime);
        System.out.println("🚀 Request bắt đầu: " + request.getRequestURI());
        return true; // cho phép tiếp tục xử lý
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long startTime = (Long) request.getAttribute(START_TIME);
        long endTime = System.currentTimeMillis();
        System.out.println("✅ Request " + request.getRequestURI() + " xử lý trong " + (endTime - startTime) + " ms");
    }
}
