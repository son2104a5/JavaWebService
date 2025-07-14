package com.data.service.b2;

import com.data.entity.b2.Flight;
import com.data.repository.b2.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Page<Flight> getFlights(int page, int size, String departure, String destination) {
        Pageable pageable = PageRequest.of(page, size);
        if ((departure != null && !departure.isEmpty()) && (destination != null && !destination.isEmpty())) {
            return flightRepository.findByDepartureAndDestinationContainingIgnoreCase(departure, destination, pageable);
        } else if (departure != null && !departure.isEmpty()) {
            return flightRepository.findByDepartureContainingIgnoreCase(departure, pageable);
        } else if (destination != null && !destination.isEmpty()) {
            return flightRepository.findByDestinationContainingIgnoreCase(destination, pageable);
        }
        return flightRepository.findAll(pageable);
    }

    @Override
    public Flight getFlightById(Integer id) {
        return flightRepository.findById(id).orElse(null);
    }
}
