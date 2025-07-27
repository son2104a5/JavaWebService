package com.data.service;

import com.data.model.entity.*;
import com.data.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepo;
    private final UserRepository userRepo;
    private final ShowtimeRepository showtimeRepo;

    public Ticket bookTicket(Long showtimeId, String seatNumber, BigDecimal price) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username).orElseThrow();

        Showtime showtime = showtimeRepo.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        Ticket ticket = Ticket.builder()
                .user(user)
                .showtime(showtime)
                .seatNumber(seatNumber)
                .bookingTime(LocalDateTime.now())
                .price(price)
                .build();

        return ticketRepo.save(ticket);
    }

    public List<Ticket> getMyTickets() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username).orElseThrow();
        return ticketRepo.findByUser(user);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }
}
