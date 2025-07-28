package com.data.controller;

import com.data.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/revenue")
    public BigDecimal getRevenue(@RequestParam String type) {
        return reportService.calculateRevenue(type);
    }
}
