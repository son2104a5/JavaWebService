package com.data.controller;

import com.data.model.entity.Country;
import com.data.repo.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryRepository countryRepo;

    @GetMapping
    public String list(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        model.addAttribute("countries", keyword != null
                ? countryRepo.findByCountryNameContainingIgnoreCase(keyword)
                : countryRepo.findAll());
        return "country/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("country", new Country());
        return "country/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Country country) {
        countryRepo.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("country", countryRepo.findById(id).orElseThrow());
        return "country/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        countryRepo.deleteById(id);
        return "redirect:/countries";
    }
}
