package com.data.controller;

import com.data.entity.Movie;
import com.data.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAll());
        return "movie/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie/add";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute("movie") Movie movie) {
        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Movie> movieOpt = movieService.findById(id);
        if (movieOpt.isPresent()) {
            model.addAttribute("movie", movieOpt.get());
            return "movie/edit";
        } else {
            return "redirect:/movies";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute("movie") Movie movie) {
        movie.setId(id);
        movieService.update(movie);
        return "redirect:/movies";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return "redirect:/movies";
    }
}
