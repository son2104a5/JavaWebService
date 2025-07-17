package com.data.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_detail_id")
    private Long productDetailId;

    @Column(name = "year_making")
    private int yearMaking;

    private String color;
    private String size;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
