package com.data.service;

import com.data.model.entity.Movie;
import com.data.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie newMovie) {
        Movie m = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        m.setTitle(newMovie.getTitle());
        m.setDescription(newMovie.getDescription());
        m.setDuration(newMovie.getDuration());
        m.setReleaseDate(newMovie.getReleaseDate());
        return movieRepository.save(m);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
