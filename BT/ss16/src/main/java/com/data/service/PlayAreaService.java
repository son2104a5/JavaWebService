package com.data.service;

import com.data.model.entity.PlayArea;
import com.data.repository.PlayAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayAreaService {
    private final PlayAreaRepository repo;

    public List<PlayArea> findAll() {
        return repo.findAll();
    }

    public Optional<PlayArea> findById(Long id) {
        return repo.findById(id);
    }

    public PlayArea save(PlayArea area) {
        return repo.save(area);
    }

    public PlayArea update(Long id, PlayArea updated) {
        PlayArea existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setMaxCapacity(updated.getMaxCapacity());
        existing.setStatus(updated.getStatus());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
