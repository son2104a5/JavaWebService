package com.data.repository;

import com.data.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserId(Long userId);

    boolean existsByUserIdAndPlayAreaId(Long userId, Long playAreaId);
    boolean existsByUserIdAndComboId(Long userId, Long comboId);
}
