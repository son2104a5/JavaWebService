package com.data.repo;

import com.data.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findByCountryNameContainingIgnoreCase(String keyword);
}
