package com.example.interceptordemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        // Giả lập login thành công -> set session
        request.getSession().setAttribute("user", "admin");
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "forbidden";
    }
}
