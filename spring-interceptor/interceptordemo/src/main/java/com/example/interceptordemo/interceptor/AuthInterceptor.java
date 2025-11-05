package com.example.interceptordemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // Giả lập: người dùng đã đăng nhập nếu có session attribute "user"
        Object user = request.getSession().getAttribute("user");

        // Nếu chưa đăng nhập
        if (user == null) {
            System.out.println("⛔ Chưa đăng nhập, chuyển hướng về /login");
            response.sendRedirect("/login");
            return false; // chặn request
        }

        // Ví dụ: nếu user không có quyền admin thì chặn
        if (!"admin".equals(user.toString()) && request.getRequestURI().startsWith("/admin")) {
            System.out.println("🚫 Không đủ quyền, chuyển hướng về /403");
            response.sendRedirect("/403");
            return false;
        }

        return true; // cho phép đi tiếp
    }
}


