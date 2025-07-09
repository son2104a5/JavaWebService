package com.data.controller;

import com.data.entity.Showtime;
import com.data.service.MovieService;
import com.data.service.ScreenRoomService;
import com.data.service.ShowtimeService;
import com.data.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ScreenRoomService screenRoomService;

    @Autowired
    private TheaterService theaterService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        model.addAttribute("movies", movieService.getAll());
        model.addAttribute("screenRooms", screenRoomService.getAll());
        return "showtime/add";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Showtime> showtimeOpt = showtimeService.findById(id);
        if (showtimeOpt.isPresent()) {
            model.addAttribute("showtime", showtimeOpt.get());
            model.addAttribute("movies", movieService.getAll());
            model.addAttribute("screenRooms", screenRoomService.getAll());
            return "showtime/edit";
        } else {
            return "redirect:/showtimes";
        }
    }

    @GetMapping
    public String listShowtimes(
            @RequestParam(name = "movieId", required = false) Long movieId,
            @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "theaterId", required = false) Long theaterId,
            @RequestParam(name = "screenRoomId", required = false) Long screenRoomId,
            Model model
    ) {
        List<Showtime> showtimes = showtimeService.filterShowtimes(movieId, date, theaterId, screenRoomId);
        model.addAttribute("showtimes", showtimes);

        model.addAttribute("movies", movieService.getAll());
        model.addAttribute("theaters", theaterService.getAll());
        model.addAttribute("screenRooms", screenRoomService.getAll());

        // Trả lại bộ lọc đã chọn để giữ trạng thái
        model.addAttribute("selectedMovieId", movieId);
        model.addAttribute("selectedDate", date);
        model.addAttribute("selectedTheaterId", theaterId);
        model.addAttribute("selectedScreenRoomId", screenRoomId);

        return "showtime/list";
    }


    @PostMapping("/add")
    public String addShowtime(@ModelAttribute("showtime") Showtime showtime) {
        showtimeService.save(showtime);
        return "redirect:/showtimes";
    }

    @PostMapping("/edit/{id}")
    public String updateShowtime(@PathVariable Long id, @ModelAttribute("showtime") Showtime showtime) {
        showtime.setId(id);
        showtimeService.update(showtime);
        return "redirect:/showtimes";
    }

    @PostMapping("/delete/{id}")
    public String deleteShowtime(@PathVariable Long id) {
        showtimeService.delete(id);
        return "redirect:/showtimes";
    }
}
