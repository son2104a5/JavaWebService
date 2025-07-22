package com.data.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
}

// Thông tin nhận đc khi truy cập:
// HTTP Status Code: 401 Unauthorized
// Header WWW-Authenticate: Basic realm="Realm"
// Body: Trống
// Console Output: Spring Boot tự động tạo ra user tạm thời và password
