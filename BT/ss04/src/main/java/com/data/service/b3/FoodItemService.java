package com.data.service.b3;

import com.data.entity.b3.Category;
import com.data.entity.b3.FoodItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FoodItemService {
    Page<FoodItem> listFoodItems(String keyword, Long categoryId, int page, int size);
    FoodItem getById(Long id);
    void save(FoodItem foodItem);
    void delete(Long id);
    List<Category> getAllCategories();
}
