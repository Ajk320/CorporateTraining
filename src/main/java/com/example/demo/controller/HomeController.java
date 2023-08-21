package com.example.demo.controller;

import java.security.Principal;

import com.example.demo.entity.TrainingSession;
import com.example.demo.repository.TrainingSessionRepo;
import com.example.demo.service.TrainingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private TrainingSessionService trainingSessionService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TrainingSessionRepo trainingSessionRepo;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/registerCourse")
    public String registerCourse() {
        return "registercourse";
    }

    @GetMapping("/signin")
    public String loginIn() {
        return "login";
    }

    @GetMapping("/user/profile")
    public String profile(Principal p, Model m) {
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        m.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/user/home")
    public String home() {
        return "home";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session) {
        //System.out.println(user);
        User user1 = userService.saveUser(user);

        if (user1 != null) {
            session.setAttribute("msg", "register successfully");
        } else {
            session.setAttribute("msg", "Something went wrong on server");
        }
        return "redirect:/register";
    }

    @PostMapping("/saveTrainingSession")
    public String saveTrainingSession(@ModelAttribute TrainingSession trainingSession, HttpSession session) {
        //System.out.println(user);
        TrainingSession trainingSession1 = trainingSessionService.saveTrainingSession(trainingSession);

        if (trainingSession1 != null) {
            session.setAttribute("msg", "registered successfully");
        } else {
            session.setAttribute("msg", "Something went wrong on server");
        }
        return "redirect:/registerCourse";
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/login";
    }

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }

    }
}

