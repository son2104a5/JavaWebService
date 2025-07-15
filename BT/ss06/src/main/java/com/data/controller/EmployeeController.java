package com.data.controller;

import com.data.entity.Employee;
import com.data.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee API", description = "Quản lý thông tin nhân viên")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Lấy danh sách nhân viên")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết nhân viên theo ID")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Thêm nhân viên mới")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Cập nhật thông tin nhân viên")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa nhân viên theo ID")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
