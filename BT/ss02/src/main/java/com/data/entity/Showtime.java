package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_room_id", nullable = false)
    private ScreenRoom screenRoom;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int numberSeatEmpty;
}
