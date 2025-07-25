package com.data.repo;

import com.data.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCityNameContainingIgnoreCase(String keyword);
}
