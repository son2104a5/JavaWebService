package com.data.service;

import com.data.model.entity.Customer;
import com.data.repository.CustomerRepository;
import com.data.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setStatus(true);
        customer.setCreatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        existing.setFullName(updatedCustomer.getFullName());
        existing.setPhone(updatedCustomer.getPhone());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setNumberOfPayments(updatedCustomer.getNumberOfPayments());
        return customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        customer.setStatus(false); // xóa mềm
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return customerRepository.findAllByStatusTrue();
    }
}
