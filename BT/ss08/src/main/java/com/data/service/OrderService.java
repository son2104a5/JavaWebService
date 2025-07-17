package com.data.service;

import com.data.model.dto.request.OrderItemDTO;
import com.data.model.dto.request.OrderRequestDTO;
import com.data.model.entity.*;
import com.data.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderDetailRepository orderDetailRepo;
    private final CustomerRepository customerRepo;
    private final EmployeeRepository employeeRepo;
    private final DishRepository dishRepo;

    @Transactional
    public Order createOrder(OrderRequestDTO dto) {
        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Khách hàng không tồn tại"));

        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Nhân viên không tồn tại"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setEmployee(employee);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderDetail> orderDetails = new ArrayList<>();
        double total = 0.0;

        for (OrderItemDTO item : dto.getItems()) {
            Dish dish = dishRepo.findById(item.getDishId())
                    .orElseThrow(() -> new EntityNotFoundException("Món ăn không tồn tại"));

            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setDish(dish);
            detail.setQuantity(item.getQuantity());
            detail.setPriceBuy(item.getPriceBuy());

            total += item.getQuantity() * item.getPriceBuy();
            orderDetails.add(detail);
        }

        order.setTotalMoney(total);
        Order savedOrder = orderRepo.save(order);

        for (OrderDetail d : orderDetails) {
            d.setOrder(savedOrder);
        }

        orderDetailRepo.saveAll(orderDetails);
        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}
