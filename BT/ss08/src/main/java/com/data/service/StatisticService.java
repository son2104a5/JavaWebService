package com.data.service;

import java.util.List;
import java.util.Map;

public interface StatisticService {
    List<Map<String, Object>> getTop10Dishes();
    List<Map<String, Object>> getTop10Customers();
    Double getCurrentMonthExpenses();
    List<Map<String, Object>> getMonthlyExpenses();
    List<Map<String, Object>> getMonthlyRevenue();
    Map<String, Object> getTopEmployeeOfMonth();
}
