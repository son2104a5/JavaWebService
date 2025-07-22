package com.data.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Public - ai cũng truy cập được";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Admin - chỉ ROLE_ADMIN mới truy cập";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "User - chỉ ROLE_USER mới truy cập";
    }
}
