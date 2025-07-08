package com.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class Hello {
    @GetMapping
    public String hello(Model model) {
        model.addAttribute("name", "Nguyễn Đắc Sơn");
        return "home";
    }
}
