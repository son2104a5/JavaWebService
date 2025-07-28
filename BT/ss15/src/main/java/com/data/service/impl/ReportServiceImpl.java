package com.data.service.impl;

import com.data.repository.OrderRepository;
import com.data.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final OrderRepository orderRepository;

    @Override
    public BigDecimal calculateRevenue(String type) {
        LocalDate now = LocalDate.now();

        switch (type) {
            case "day":
                return orderRepository.sumRevenueByDate(now);
            case "month":
                return orderRepository.sumRevenueByMonth(now.getYear(), now.getMonthValue());
            case "year":
                return orderRepository.sumRevenueByYear(now.getYear());
            default:
                throw new RuntimeException("Loại báo cáo không hợp lệ");
        }
    }
}
