package com.data.repository;

import com.data.model.entity.TicketOrder;
import com.data.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
    List<TicketOrder> findByUser(User user);
    List<TicketOrder> findByUserId(Long userId);
}
