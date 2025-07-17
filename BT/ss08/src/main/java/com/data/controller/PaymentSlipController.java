package com.data.controller;

import com.data.model.entity.PaymentSlip;
import com.data.service.PaymentSlipService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentslips")
@RequiredArgsConstructor
public class PaymentSlipController {
    private final PaymentSlipService paymentSlipService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PaymentSlip slip) {
        try {
            PaymentSlip created = paymentSlipService.create(slip);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi tạo phiếu chi: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PaymentSlip slip) {
        try {
            PaymentSlip updated = paymentSlipService.update(id, slip);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentSlip>> getAll() {
        return ResponseEntity.ok(paymentSlipService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            paymentSlipService.delete(id);
            return ResponseEntity.ok("Xóa phiếu chi thành công");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
