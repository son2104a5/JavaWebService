package com.data.service;

import com.data.model.entity.Order;
import com.data.model.entity.User;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order, User customer);
    List<Order> getMyOrders(User customer);
    List<Order> getAllOrders();
    Order updateStatus(Long orderId, String status);
}
