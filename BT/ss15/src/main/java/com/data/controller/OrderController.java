package com.data.controller;

import com.data.model.entity.Order;
import com.data.model.entity.User;
import com.data.security.principle.CustomUserDetailsService;
import com.data.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order> create(@RequestBody Order order,
                                        @AuthenticationPrincipal CustomUserDetailsService userDetails) {
        User customer = userDetails.toUser();
        return ResponseEntity.ok(orderService.createOrder(order, customer));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Order>> myOrders(@AuthenticationPrincipal CustomUserDetailsService userDetails) {
        User customer = userDetails.toUser();
        return ResponseEntity.ok(orderService.getMyOrders(customer));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<List<Order>> allOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }
}
