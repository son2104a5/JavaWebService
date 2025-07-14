package com.data.entity.b3;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
