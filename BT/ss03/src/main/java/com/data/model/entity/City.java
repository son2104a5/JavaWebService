package com.data.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    private String cityName;
    private String sesson;
    private Double area;
    private Long population;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
