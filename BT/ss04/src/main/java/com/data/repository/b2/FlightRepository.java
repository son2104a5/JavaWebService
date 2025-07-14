package com.data.repository.b2;

import com.data.entity.b2.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Page<Flight> findByDepartureAndDestinationContainingIgnoreCase(String departure, String destination, Pageable pageable);
    Page<Flight> findByDepartureContainingIgnoreCase(String departure, Pageable pageable);
    Page<Flight> findByDestinationContainingIgnoreCase(String destination, Pageable pageable);
}
