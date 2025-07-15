package com.data.controller;

import com.data.entity.Order;
import com.data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/date/{date}")
    public List<Order> getOrdersByDate(@PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return orderService.getOrdersByDate(parsedDate);
    }
}
