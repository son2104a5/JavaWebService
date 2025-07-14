package com.data.service.b3;

import com.data.entity.b3.Category;
import com.data.entity.b3.FoodItem;
import com.data.repository.b3.CategoryRepository;
import com.data.repository.b3.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepo;
    @Autowired private CategoryRepository categoryRepo;

    @Override
    public Page<FoodItem> listFoodItems(String keyword, Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null && !keyword.isEmpty()) {
            if (categoryId != null && categoryId > 0)
                return foodItemRepo.findByNameContainingIgnoreCaseAndCategory_Id(keyword, categoryId, pageable);
            return foodItemRepo.findByNameContainingIgnoreCase(keyword, pageable);
        }
        return foodItemRepo.findAll(pageable);
    }

    @Override
    public FoodItem getById(Long id) {
        return foodItemRepo.findById(id).orElse(null);
    }

    @Override
    public void save(FoodItem foodItem) {
        foodItemRepo.save(foodItem);
    }

    @Override
    public void delete(Long id) {
        foodItemRepo.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
