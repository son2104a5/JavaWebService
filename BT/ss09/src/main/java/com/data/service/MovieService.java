package com.data.service;

import com.data.model.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie addMovie(Movie movie);
    List<Movie> getMovies(String searchMove);
}
