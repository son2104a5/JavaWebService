package com.data.service;

import com.data.model.dto.EmployeeDTO;
import com.data.model.entity.Employee;
import com.data.model.projection.EmployeeInfo;
import com.data.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public  List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> searchAndSort(String phone, String sortField, String sortDir, int page) {
        Pageable pageable = PageRequest.of(page, 5,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());

        if (phone != null && !phone.trim().isEmpty()) {
            return employeeRepository.findByPhoneNumber(phone, pageable);
        }
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> findEmployeesWithSalaryGreaterThan(double salary) {
        return employeeRepository.findEmployeesWithSalaryGreaterThan(salary);
    }

    @Override
    public List<EmployeeDTO> findEmployeeDTOWithSalaryGreaterThan(double salary) {
        return employeeRepository.findEmployeeDTOWithSalaryGreaterThan(salary);
    }

    @Override
    public List<EmployeeInfo> findEmployeeInfoBySalaryGreaterThan(double salary) {
        return employeeRepository.findEmployeeInfoBySalaryGreaterThan(salary);
    }

    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setEmail(employeeDTO.getEmail());
        employee.setSalary(employeeDTO.getSalary());

        try {
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean editEmployee(int id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return false;
        }

        employee.setName(employeeDTO.getName());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setEmail(employeeDTO.getEmail());
        employee.setSalary(employeeDTO.getSalary());

        try {
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public EmployeeDTO findEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return null;
        }
        return new EmployeeDTO(employee.getId(), employee.getName(), employee.getPhoneNumber(), employee.getEmail(), employee.getSalary());
    }

    @Override
    public boolean deleteEmployee(int id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
