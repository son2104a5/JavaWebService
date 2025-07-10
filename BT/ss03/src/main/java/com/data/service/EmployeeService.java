package com.data.service;

import com.data.model.dto.EmployeeDTO;
import com.data.model.entity.Employee;
import com.data.model.projection.EmployeeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeService {
    public Page<Employee> searchAndSort(String phone, String sortField, String sortDir, int page);

    List<Employee> findEmployeesWithSalaryGreaterThan(double salary);

    List<EmployeeDTO> findEmployeeDTOWithSalaryGreaterThan(double salary);

    List<EmployeeInfo> findEmployeeInfoBySalaryGreaterThan(double salary);

    boolean addEmployee(EmployeeDTO employeeDTO);

    boolean editEmployee(int id, EmployeeDTO employeeDTO);

    EmployeeDTO findEmployeeById(int id);

    boolean deleteEmployee(int id);

    List<Employee> findAll();
}
