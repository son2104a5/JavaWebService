package com.data.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    private Integer quantityTicket;

    @ManyToMany
    @JoinTable(
        name = "ticket_order_combo",
        joinColumns = @JoinColumn(name = "ticket_order_id"),
        inverseJoinColumns = @JoinColumn(name = "combo_id")
    )
    private List<Combo> combos;

    private BigDecimal totalMoney;

    private LocalDateTime createdAt;
}
