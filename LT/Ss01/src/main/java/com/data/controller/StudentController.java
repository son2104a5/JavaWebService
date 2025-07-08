package com.data.controller;

import com.data.repo.StudentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping
    public String home(Model model) {
        model.addAttribute("list", StudentRepo.getStudents());
        return "home";
    }


}
