package com.data.service;

import com.data.model.entity.Employee;
import com.data.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepo;

    public Employee addEmployee(Employee emp) {
        return employeeRepo.save(emp);
    }

    public Employee updateEmployee(Long id, Employee empData) {
        Employee emp = employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        emp.setFullname(empData.getFullname());
        emp.setPhone(empData.getPhone());
        emp.setAddress(empData.getAddress());
        emp.setPosition(empData.getPosition());
        emp.setSalary(empData.getSalary());

        return employeeRepo.save(emp);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepo.existsById(id))
            throw new EntityNotFoundException("Employee not found");
        employeeRepo.deleteById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }
}
