package com.data.controller.b2;

import com.data.service.b2.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public String flight(Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                         @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                         @RequestParam(value = "departure", required = false, defaultValue = "") String departure,
                         @RequestParam(value = "destination", required = false, defaultValue = "") String destination) {
        model.addAttribute("flights", flightService.getFlights(page, size, departure, destination));
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        model.addAttribute("totalPages", flightService.getFlights(page, size, departure, destination).getTotalPages());
        return "b2/flights";
    }
}
