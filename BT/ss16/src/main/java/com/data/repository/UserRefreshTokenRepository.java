package com.data.repository;

import com.data.model.entity.User;
import com.data.model.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    Optional<UserRefreshToken> findByTokenRefesh(String tokenRefesh);
    void deleteByUser(User user);
}
