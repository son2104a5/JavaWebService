package com.data.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {

    @PostMapping
    public Map<String, Object> createOrder(@RequestParam String product, @RequestParam int quantity) {
        Map<String, Object> response = new HashMap<>();
        response.put("product", product);
        response.put("quantity", quantity);
        response.put("status", "created");

        // Log structured thông tin quan trọng
        log.info("Order created | product={} | quantity={} | status={}", product, quantity, "created");

        return response;
    }
}
