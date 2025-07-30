package com.data.controller;

import com.data.model.entity.PlayArea;
import com.data.service.PlayAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/play-areas")
@RequiredArgsConstructor
public class PlayAreaController {
    private final PlayAreaService service;

    // Mọi user đều có thể xem danh sách
    @GetMapping
    public ResponseEntity<List<PlayArea>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // Chỉ ADMIN hoặc STAFF mới được thêm
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PostMapping
    public ResponseEntity<PlayArea> create(@RequestBody PlayArea area) {
        return ResponseEntity.ok(service.save(area));
    }

    // Chỉ ADMIN hoặc STAFF mới được sửa
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<PlayArea> update(@PathVariable Long id, @RequestBody PlayArea area) {
        return ResponseEntity.ok(service.update(id, area));
    }

    // Chỉ ADMIN hoặc STAFF mới được xóa
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Xóa thành công");
    }
}
