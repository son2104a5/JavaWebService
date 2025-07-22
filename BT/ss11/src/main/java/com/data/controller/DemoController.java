package com.data.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Public OK!";
    }

    @GetMapping("/private/secret")
    public String secret() {
        return "Private OK!";
    }
}
