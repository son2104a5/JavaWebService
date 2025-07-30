package com.data.controller;

import com.data.model.entity.Combo;
import com.data.service.ComboService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combos")
@RequiredArgsConstructor
public class ComboController {
    private final ComboService comboService;

    // Mọi user đều được xem
    @GetMapping
    public ResponseEntity<List<Combo>> getAll() {
        return ResponseEntity.ok(comboService.findAll());
    }

    // Chỉ ADMIN/STAFF được thêm mới
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @PostMapping
    public ResponseEntity<Combo> create(@RequestBody Combo combo) {
        return ResponseEntity.ok(comboService.create(combo));
    }

    // Chỉ ADMIN/STAFF được sửa
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<Combo> update(@PathVariable Long id, @RequestBody Combo combo) {
        return ResponseEntity.ok(comboService.update(id, combo));
    }

    // Chỉ ADMIN/STAFF được xóa
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        comboService.delete(id);
        return ResponseEntity.ok("Xóa combo thành công");
    }
}
