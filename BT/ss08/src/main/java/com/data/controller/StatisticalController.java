package com.data.controller;

import com.data.model.dto.response.DataResponse;
import com.data.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
public class StatisticalController {
    @Autowired
    private StatisticService statisticalService;

    @GetMapping("/top-dishes")
    public ResponseEntity<DataResponse> getTopDishes() {
        return new ResponseEntity<>(new DataResponse("Top 10 món ăn bán chạy nhất", statisticalService.getTop10Dishes()), HttpStatus.OK);
    }

    @GetMapping("/top-customers")
    public ResponseEntity<DataResponse> getTopCustomers() {
        return ResponseEntity.ok(
            new DataResponse("Top 10 khách hàng tiêu nhiều tiền nhất", statisticalService.getTop10Customers())
        );
    }

    @GetMapping("/current-month-expenses")
    public ResponseEntity<DataResponse> getCurrentMonthExpenses() {
        return ResponseEntity.ok(
            new DataResponse("Tổng chi phí tháng hiện tại", statisticalService.getCurrentMonthExpenses())
        );
    }

    @GetMapping("/monthly-expenses")
    public ResponseEntity<DataResponse> getMonthlyExpenses() {
        return ResponseEntity.ok(
            new DataResponse("Chi phí từng tháng trong năm", statisticalService.getMonthlyExpenses())
        );
    }

    @GetMapping("/monthly-revenue")
    public ResponseEntity<DataResponse> getMonthlyRevenue() {
        return ResponseEntity.ok(
            new DataResponse("Doanh thu theo tháng", statisticalService.getMonthlyRevenue())
        );
    }

    @GetMapping("/top-employee")
    public ResponseEntity<DataResponse> getTopEmployee() {
        return ResponseEntity.ok(
            new DataResponse("Nhân viên có doanh thu cao nhất tháng", statisticalService.getTopEmployeeOfMonth())
        );
    }
}
