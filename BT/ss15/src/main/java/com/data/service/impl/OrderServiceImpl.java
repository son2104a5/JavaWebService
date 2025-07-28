package com.data.service.impl;

import com.data.model.entity.Order;
import com.data.model.entity.OrderItem;
import com.data.model.entity.Product;
import com.data.model.entity.User;
import com.data.model.enums.OrderStatus;
import com.data.repository.OrderItemRepository;
import com.data.repository.OrderRepository;
import com.data.repository.ProductRepository;
import com.data.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Override
    public Order createOrder(Order order, User customer) {
        order.setUser(customer);
        order.setCreatedDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : order.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sản phẩm"));

            item.setOrder(order);
            item.setPriceBuy(product.getPrice());
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        order.setTotalMoney(total);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getMyOrders(User customer) {
        return orderRepository.findByUser(customer);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn hàng"));
        order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        return orderRepository.save(order);
    }
}
