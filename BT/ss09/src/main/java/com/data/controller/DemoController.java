package com.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/divide")
    public String divide(@RequestParam int a, @RequestParam int b) {
        logger.info("Request received: divide {} / {}", a, b);
        int result = a / b; // Nếu b = 0 sẽ gây lỗi
        return "Result: " + result;
    }
}
