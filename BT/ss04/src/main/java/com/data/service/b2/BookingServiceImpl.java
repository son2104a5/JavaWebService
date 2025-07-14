package com.data.service.b2;

import com.data.entity.b2.Booking;
import com.data.entity.b2.BookingStatus;
import com.data.repository.b2.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Page<Booking> getBookings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookingRepository.findAll(pageable);
    }

    @Override
    public Booking getBookingById(Integer id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addBooking(Booking booking) {
        try {
            booking.setStatus(BookingStatus.PENDING);
            booking.setBookingTime(LocalDateTime.now());
            bookingRepository.save(booking);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean cancelledBooking(Booking booking) {
        try {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
