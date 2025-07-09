package com.data.service;

import com.data.entity.Movie;
import com.data.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IService<Movie> {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie save(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
