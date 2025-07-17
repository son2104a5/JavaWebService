package com.data.service;

import com.data.model.dto.request.DishDTO;
import com.data.model.entity.Dish;
import com.data.repository.DishRepository;
import com.data.service.CloudinaryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepo;
    private final CloudinaryService cloudinaryService;

    public Dish addDish(DishDTO dto) throws IOException {
        String imageUrl = cloudinaryService.uploadFile(dto.getImage());
        Dish dish = new Dish(null, dto.getName(), dto.getDescription(), dto.getPrice(), dto.getStatus(), dto.getImage().isEmpty());
        return dishRepo.save(dish);
    }

    public Dish updateDish(Long id, DishDTO dto) throws IOException {
        Dish dish = dishRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Dish not found"));

        dish.setName(dto.getName());
        dish.setDescription(dto.getDescription());
        dish.setPrice(dto.getPrice());
        dish.setStatus(Boolean.valueOf(dto.getStatus()));

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String newImageUrl = cloudinaryService.uploadFile(dto.getImage());
            dish.setImageUrl(newImageUrl);
        }

        return dishRepo.save(dish);
    }

    public void deleteDish(Long id) {
        if (!dishRepo.existsById(id))
            throw new EntityNotFoundException("Dish not found");
        dishRepo.deleteById(id);
    }

    public List<Dish> getAllDishes() {
        return dishRepo.findAll();
    }
}
