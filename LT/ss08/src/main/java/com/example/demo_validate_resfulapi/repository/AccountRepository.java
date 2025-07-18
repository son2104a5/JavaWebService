package com.example.demo_validate_resfulapi.repository;

import com.example.demo_validate_resfulapi.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
