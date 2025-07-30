package com.data.service;

import com.data.model.entity.Combo;
import com.data.repository.ComboRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComboService {
    private final ComboRepository comboRepo;

    public List<Combo> findAll() {
        return comboRepo.findAll();
    }

    public Combo findById(Long id) {
        return comboRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy combo"));
    }

    public Combo create(Combo combo) {
        return comboRepo.save(combo);
    }

    public Combo update(Long id, Combo combo) {
        Combo existing = findById(id);
        existing.setName(combo.getName());
        existing.setDescription(combo.getDescription());
        existing.setPrice(combo.getPrice());
        existing.setItems(combo.getItems());
        existing.setStatus(combo.getStatus());
        return comboRepo.save(existing);
    }

    public void delete(Long id) {
        comboRepo.deleteById(id);
    }
}
