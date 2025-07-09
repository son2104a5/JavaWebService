package com.data.service;

import com.data.entity.Theater;
import com.data.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService implements IService<Theater> {

    @Autowired
    private TheaterRepository theaterRepository;

    public List<Theater> getAll() {
        return theaterRepository.findAll();
    }

    @Override
    public Theater save(Theater entity) {
        return theaterRepository.save(entity);
    }

    @Override
    public Optional<Theater> findById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public Theater update(Theater entity) {
        return theaterRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        theaterRepository.deleteById(id);
    }
}
