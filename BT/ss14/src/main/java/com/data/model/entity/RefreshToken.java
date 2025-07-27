package com.data.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String addressIp;

    @Column(unique = true, nullable = false)
    private String token;

    private LocalDateTime expiryDate;

    private LocalDateTime createdAt;
}
