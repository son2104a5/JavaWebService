package com.data.controller;

import com.data.model.dto.EmployeeDTO;
import com.data.model.entity.Employee;
import com.data.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String listEmployees(Model model,
                                @RequestParam(value = "phone", required = false) String phone,
                                @RequestParam(value = "sort", defaultValue = "name") String sort,
                                @RequestParam(value = "dir", defaultValue = "asc") String dir,
                                @RequestParam(value = "page", defaultValue = "0") int page) {
        // Gọi service xử lý tìm kiếm, phân trang, sắp xếp
        Page<Employee> employees = employeeService.searchAndSort(phone, sort, dir, page);
        model.addAttribute("employees", employees);
        model.addAttribute("phone", phone);
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        return "b9/employees";
    }

    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        int currentId = employeeService.findAll().size() + 1;
        model.addAttribute("employee", new EmployeeDTO(currentId, "", "", "", 0.0));
        return "b9/add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(EmployeeDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(Model model, @PathVariable int id) {
        EmployeeDTO employeeDTO = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employeeDTO);
        return "b9/edit-employee";
    }

    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, EmployeeDTO employeeDTO) {
        employeeService.editEmployee(id, employeeDTO);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
