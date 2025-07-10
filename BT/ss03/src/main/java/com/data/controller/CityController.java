package com.data.controller;

import com.data.model.entity.City;
import com.data.model.entity.Country;
import com.data.repo.CityRepository;
import com.data.repo.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityRepository cityRepo;
    private final CountryRepository countryRepo;

    @GetMapping
    public String list(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        model.addAttribute("cities", keyword != null
                ? cityRepo.findByCityNameContainingIgnoreCase(keyword)
                : cityRepo.findAll());
        return "city/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("countries", countryRepo.findAll());
        return "city/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute City city) {
        cityRepo.save(city);
        return "redirect:/cities";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("city", cityRepo.findById(id).orElseThrow());
        model.addAttribute("countries", countryRepo.findAll());
        return "city/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        cityRepo.deleteById(id);
        return "redirect:/cities";
    }
}
