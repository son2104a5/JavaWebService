package com.data.repository;

import com.data.model.entity.RefreshToken;
import com.data.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByToken(String token);
    List<RefreshToken> findByUserOrderByCreatedAtAsc(User user);
    void deleteByUser(User user);

}
