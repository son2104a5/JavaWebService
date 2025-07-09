package com.data.service;

import com.data.entity.Showtime;
import com.data.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService implements IService<Showtime> {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Showtime> getAll() {
        return showtimeRepository.findAll();
    }

    public List<Showtime> filterShowtimes(Long movieId, LocalDate date, Long theaterId, Long screenRoomId) {
        List<Showtime> all = showtimeRepository.findAll();
        return all.stream()
                .filter(s -> movieId == null || s.getMovie().getId().equals(movieId))
                .filter(s -> date == null || s.getStartTime().toLocalDate().equals(date))
                .filter(s -> theaterId == null || s.getScreenRoom().getTheater().getId().equals(theaterId))
                .filter(s -> screenRoomId == null || s.getScreenRoom().getId().equals(screenRoomId))
                .toList();
    }


    @Override
    public Showtime save(Showtime entity) {
        return showtimeRepository.save(entity);
    }

    @Override
    public Optional<Showtime> findById(Long id) {
        return showtimeRepository.findById(id);
    }

    @Override
    public Showtime update(Showtime entity) {
        return showtimeRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        showtimeRepository.deleteById(id);
    }
}
