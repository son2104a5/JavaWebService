package com.data.entity.b2;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customerName;
    private String customerPhone;
    private LocalDateTime bookingTime;
    @Column(columnDefinition = "ENUM('PENDING', 'CONFIRMED', 'CANCELLED')", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;
}
