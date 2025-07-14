package com.data.service.b2;

import com.data.entity.b2.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightService {
    Page<Flight> getFlights(int page, int size, String departure, String destination);
    Flight getFlightById(Integer id);
}
