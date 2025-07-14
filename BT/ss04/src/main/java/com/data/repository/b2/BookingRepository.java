package com.data.repository.b2;

import com.data.entity.b2.Booking;
import com.data.entity.b2.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
