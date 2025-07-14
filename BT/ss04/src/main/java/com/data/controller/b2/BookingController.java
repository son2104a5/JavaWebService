package com.data.controller.b2;

import com.data.entity.b2.Booking;
import com.data.entity.b2.Flight;
import com.data.service.b2.BookingService;
import com.data.service.b2.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private FlightService flightService;

    @GetMapping
    public String booking(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                          @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        model.addAttribute("bookings", bookingService.getBookings(page, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", bookingService.getBookings(page, size).getTotalPages());
        return "b2/bookings";
    }

    @GetMapping("/add/{id}")
    public String addBookingForm(@PathVariable int id, Model model) {
        Flight flight = flightService.getFlightById(id);
        Booking booking = new Booking();
        booking.setFlight(flight);
        model.addAttribute("booking", booking);
        return "b2/add";
    }

    @PostMapping("/add")
    public String addBooking(@ModelAttribute Booking booking, Model model) {
        if (bookingService.addBooking(booking)) {
            return "redirect:/booking";
        } else {
            model.addAttribute("error", "Failed to add booking");
            return "b2/add";
        }
    }

    @GetMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable("id") Integer id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            if (bookingService.cancelledBooking(booking)) {
                return "redirect:/booking";
            } else {
                model.addAttribute("error", "Failed to cancel booking");
            }
        } else {
            model.addAttribute("error", "Booking not found");
        }
        return "b2/bookings";
    }
}
