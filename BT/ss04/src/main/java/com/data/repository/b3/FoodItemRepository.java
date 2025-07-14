package com.data.repository.b3;

import com.data.entity.b3.FoodItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    Page<FoodItem> findByNameContainingIgnoreCaseAndCategory_Id(String name, Long categoryId, Pageable pageable);
    Page<FoodItem> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
