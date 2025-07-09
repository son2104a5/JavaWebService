package com.data.service;

import com.data.entity.Seat;
import com.data.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatService implements IService<Seat> {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Seat save(Seat entity) {
        return seatRepository.save(entity);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public Seat update(Seat entity) {
        return seatRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        seatRepository.deleteById(id);
    }
}
