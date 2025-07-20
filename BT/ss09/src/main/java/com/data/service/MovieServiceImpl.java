package com.data.service;

import com.data.model.entity.Movie;
import com.data.repo.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Override
    public Movie addMovie(Movie movie) {
        try {
            Movie saved = movieRepository.save(movie);
            logger.info("Đã thêm phim: {} lúc {}", saved.getTitle(), LocalDateTime.now());
            return saved;
        } catch (Exception ex) {
            logger.error("Lỗi khi thêm phim: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public List<Movie> getMovies(String searchMove) {
        try {
            if (searchMove == null || searchMove.isEmpty()) {
                logger.info("Lấy tất cả phim");
                return movieRepository.findAll();
            } else {
                logger.info("Tìm kiếm phim với từ khóa: {}", searchMove);
                return movieRepository.findByTitleContainingIgnoreCase(searchMove);
            }
        } catch (Exception ex) {
            logger.error("Lỗi khi lấy danh sách phim: {}", ex.getMessage(), ex);
            throw ex;
        }
    }
}
