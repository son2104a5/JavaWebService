package com.data.service.order;

import com.data.model.entity.Order;
import com.data.repository.OrderDetailRepository;
import com.data.repository.OrderRepository;
import com.data.repository.ProductRepository;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot found order with id: " + id));
    }

    @Override
    public Order save(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order update(Order order, Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return true;
    }
}
