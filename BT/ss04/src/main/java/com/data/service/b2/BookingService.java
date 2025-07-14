package com.data.service.b2;

import com.data.entity.b2.Booking;
import org.springframework.data.domain.Page;

public interface BookingService {
    Page<Booking> getBookings(int page, int size);
    boolean addBooking(Booking booking);
    boolean cancelledBooking(Booking booking);
    Booking getBookingById(Integer id);
}
