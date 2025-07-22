package com.data.repo;

import com.data.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByCccdContainingIgnoreCase(String cccd);
    List<Account> findByPhoneContainingIgnoreCase(String phone);
}
