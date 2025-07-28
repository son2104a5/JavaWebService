package com.data.service;

import java.math.BigDecimal;

public interface ReportService {
    BigDecimal calculateRevenue(String type); // "day", "month", "year"
}
