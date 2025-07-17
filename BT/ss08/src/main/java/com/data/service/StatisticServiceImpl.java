package com.data.service;

import com.data.service.StatisticService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Map<String, Object>> getTop10Dishes() {
        String sql = """
            SELECT d.name AS dishName, SUM(od.quantity) AS totalSold
            FROM order_detail od
            JOIN dish d ON d.id = od.dish_id
            GROUP BY d.id
            ORDER BY totalSold DESC
            LIMIT 10
        """;
        Query query = entityManager.createNativeQuery(sql, "dishStatsMapping");
        return query.getResultList();
    }

    @Override
    public List<Map<String, Object>> getTop10Customers() {
        String sql = """
            SELECT c.full_name AS customerName, SUM(o.total_money) AS totalSpent
            FROM orders o
            JOIN customer c ON o.customer_id = c.id
            GROUP BY c.id
            ORDER BY totalSpent DESC
            LIMIT 10
        """;
        Query query = entityManager.createNativeQuery(sql, "customerStatsMapping");
        return query.getResultList();
    }

    @Override
    public Double getCurrentMonthExpenses() {
        String sql = """
            SELECT COALESCE(SUM(money), 0) FROM payment_slip 
            WHERE MONTH(created_at) = :month AND YEAR(created_at) = :year
        """;
        Query query = entityManager.createNativeQuery(sql);
        LocalDate now = LocalDate.now();
        query.setParameter("month", now.getMonthValue());
        query.setParameter("year", now.getYear());
        return ((Number) query.getSingleResult()).doubleValue();
    }

    @Override
    public List<Map<String, Object>> getMonthlyExpenses() {
        String sql = """
            SELECT MONTH(created_at) AS month, SUM(money) AS total
            FROM payment_slip
            WHERE YEAR(created_at) = :year
            GROUP BY MONTH(created_at)
        """;
        Query query = entityManager.createNativeQuery(sql, "monthlyStatsMapping");
        query.setParameter("year", Year.now().getValue());
        return query.getResultList();
    }

    @Override
    public List<Map<String, Object>> getMonthlyRevenue() {
        String sql = """
            SELECT MONTH(created_at) AS month, SUM(total_money) AS total
            FROM orders
            WHERE YEAR(created_at) = :year
            GROUP BY MONTH(created_at)
        """;
        Query query = entityManager.createNativeQuery(sql, "monthlyStatsMapping");
        query.setParameter("year", Year.now().getValue());
        return query.getResultList();
    }

    @Override
    public Map<String, Object> getTopEmployeeOfMonth() {
        String sql = """
            SELECT e.full_name AS employeeName, SUM(o.total_money) AS revenue
            FROM orders o
            JOIN employee e ON o.employee_id = e.id
            WHERE MONTH(o.created_at) = :month AND YEAR(o.created_at) = :year
            GROUP BY e.id
            ORDER BY revenue DESC
            LIMIT 1
        """;
        Query query = entityManager.createNativeQuery(sql, "topEmployeeMapping");
        query.setParameter("month", LocalDate.now().getMonthValue());
        query.setParameter("year", LocalDate.now().getYear());
        return (Map<String, Object>) query.getSingleResult();
    }
}
