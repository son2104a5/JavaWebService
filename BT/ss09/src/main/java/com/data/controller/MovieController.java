package com.data.controller;

import com.data.model.entity.Movie;
import com.data.repo.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @GetMapping
    public ResponseEntity<?> getMovies(@RequestParam(required = false) String searchMove) {
        try {
            if (searchMove == null || searchMove.isEmpty()) {
                logger.info("🎬 Lấy tất cả phim - Thời gian: {}", LocalDate.now());
                return ResponseEntity.ok(movieRepository.findAll());
            } else {
                logger.info("🎬 Tìm kiếm phim với từ khóa: [{}] - Thời gian: {}", searchMove, LocalDate.now());
                return ResponseEntity.ok(movieRepository.findByTitleContainingIgnoreCase(searchMove));
            }
        } catch (Exception e) {
            logger.error("\u001B[31mLỗi khi lấy danh sách phim: {}\u001B[0m", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        try {
            Movie saved = movieRepository.save(movie);
            logger.info("🎬 Thêm phim thành công: [{}] - Thời gian: {}", saved.getTitle(), LocalDate.now());
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            logger.error("\u001B[31mLỗi khi thêm phim: {}\u001B[0m", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie newMovie) {
        try {
            Optional<Movie> optional = movieRepository.findById(id);
            if (optional.isEmpty()) {
                logger.error("\u001B[31mKhông tìm thấy phim với ID {}\u001B[0m", id);
                return ResponseEntity.notFound().build();
            }

            Movie oldMovie = optional.get();
            logger.warn("\u001B[33mThông tin cũ: {}\u001B[0m", oldMovie);

            oldMovie.setTitle(newMovie.getTitle());
            oldMovie.setDescription(newMovie.getDescription());
            oldMovie.setReleaseDate(newMovie.getReleaseDate());
            oldMovie.setPoster(newMovie.getPoster());

            Movie updated = movieRepository.save(oldMovie);
            logger.info("\u001B[32mThông tin mới: {}\u001B[0m", updated);

            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            logger.error("\u001B[31mLỗi khi cập nhật phim: {}\u001B[0m", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        try {
            Optional<Movie> optional = movieRepository.findById(id);
            if (optional.isEmpty()) {
                logger.error("\u001B[31mKhông tìm thấy phim với ID {}\u001B[0m", id);
                return ResponseEntity.notFound().build();
            }

            Movie deleted = optional.get();
            movieRepository.deleteById(id);
            logger.error("\u001B[31mXóa thành công\u001B[0m - \u001B[32mPhim đã xóa: {}\u001B[0m", deleted);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("\u001B[31mLỗi khi xóa phim: {}\u001B[0m", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
