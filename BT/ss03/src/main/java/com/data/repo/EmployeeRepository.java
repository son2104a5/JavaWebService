package com.data.repo;

import com.data.model.entity.Employee;
import com.data.model.dto.EmployeeDTO;
import com.data.model.projection.EmployeeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findByPhoneNumber(String phoneNumber, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeesWithSalaryGreaterThan(double salary);

    @Query("SELECT new com.data.model.dto.EmployeeDTO(e.name, e.phoneNumber, e.email, e.salary) " +
            "FROM Employee e WHERE e.salary > :salary")
    List<EmployeeDTO> findEmployeeDTOWithSalaryGreaterThan(double salary);

    @Query("SELECT e.name AS name, e.phoneNumber AS phone, e.salary AS salary " +
            "FROM Employee e WHERE e.salary > :salary")
    List<EmployeeInfo> findEmployeeInfoBySalaryGreaterThan(double salary);
}
