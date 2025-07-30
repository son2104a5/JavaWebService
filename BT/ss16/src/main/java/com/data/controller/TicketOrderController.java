package com.data.controller;

import com.data.model.entity.TicketOrder;
import com.data.model.entity.User;
import com.data.service.TicketOrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-orders")
@RequiredArgsConstructor
public class TicketOrderController {
    private final TicketOrderService orderService;

    // ROLE_USER đặt vé
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<TicketOrder> placeOrder(@AuthenticationPrincipal User user,
                                                  @RequestBody TicketOrderRequest req) {
        TicketOrder order = orderService.placeOrder(user, req.getQuantityTicket(), req.getComboIds());
        return ResponseEntity.ok(order);
    }

    // ROLE_USER xem lịch sử
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my")
    public ResponseEntity<List<TicketOrder>> getMyOrders(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.getMyOrders(user));
    }

    // ADMIN/STAFF xem toàn bộ
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<TicketOrder>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Data
    public static class TicketOrderRequest {
        private Integer quantityTicket;
        private List<Long> comboIds;
    }
}
