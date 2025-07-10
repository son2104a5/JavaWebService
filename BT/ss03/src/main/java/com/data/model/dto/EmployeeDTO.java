package com.data.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private int id;

    private String name;

    private String phoneNumber;

    private String email;

    private Double salary;

    public EmployeeDTO(String name, String phoneNumber, String email, Double salary) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
    }
}
