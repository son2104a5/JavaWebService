package com.data.controller;

import com.data.model.entity.Ticket;
import com.data.service.TicketService;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<Ticket> bookTicket(@RequestBody TicketRequest request) {
        Ticket ticket = ticketService.bookTicket(
                request.getShowtimeId(),
                request.getSeatNumber(),
                request.getPrice()
        );
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Ticket>> myTickets() {
        return ResponseEntity.ok(ticketService.getMyTickets());
    }

    @GetMapping("/admin/tickets")
    public ResponseEntity<List<Ticket>> allTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @Getter @Setter
    public static class TicketRequest {
        @NotNull
        private Long showtimeId;

        @NotBlank
        private String seatNumber;

        @NotNull
        @DecimalMin("0.0")
        private BigDecimal price;
    }
}
