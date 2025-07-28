package com.data.repository;

import com.data.model.entity.Order;
import com.data.model.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    @Query("SELECT SUM(o.totalMoney) FROM Order o WHERE DATE(o.createdDate) = :date")
    BigDecimal sumRevenueByDate(@Param("date") LocalDate date);

    @Query("SELECT SUM(o.totalMoney) FROM Order o WHERE YEAR(o.createdDate) = :year AND MONTH(o.createdDate) = :month")
    BigDecimal sumRevenueByMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT SUM(o.totalMoney) FROM Order o WHERE YEAR(o.createdDate) = :year")
    BigDecimal sumRevenueByYear(@Param("year") int year);
}

