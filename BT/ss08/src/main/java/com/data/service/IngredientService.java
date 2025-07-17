package com.data.service;

import com.data.model.dto.request.IngredientDTO;
import com.data.model.entity.Ingredient;
import com.data.repository.IngredientRepository;
import com.data.service.CloudinaryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepo;
    private final CloudinaryService cloudinaryService;

    public Ingredient addIngredient(IngredientDTO dto) throws IOException {
        String imageUrl = cloudinaryService.uploadFile(dto.getImage());
        Ingredient ing = new Ingredient(null, dto.getName(), dto.getStock(), dto.getExpiry(), imageUrl);
        return ingredientRepo.save(ing);
    }

    public Ingredient updateIngredient(Long id, IngredientDTO dto) throws IOException {
        Ingredient ing = ingredientRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

        ing.setName(dto.getName());
        ing.setStock(dto.getStock());
        ing.setExpiry(dto.getExpiry());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(dto.getImage());
            ing.setImage(imageUrl);
        }

        return ingredientRepo.save(ing);
    }

    public void deleteIngredient(Long id) {
        if (!ingredientRepo.existsById(id))
            throw new EntityNotFoundException("Ingredient not found");
        ingredientRepo.deleteById(id);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepo.findAll();
    }
}
