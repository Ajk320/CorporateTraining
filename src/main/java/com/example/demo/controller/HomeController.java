package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Enrollment;
import com.example.demo.entity.TrainingSession;
import com.example.demo.repository.EnrollmentRepo;
import com.example.demo.repository.TrainingSessionRepo;
import com.example.demo.service.EnrollmentService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private TrainingSessionService trainingSessionService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TrainingSessionRepo trainingSessionRepo;

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @GetMapping("/")
    public String index() {
        return "login";
    }
    @GetMapping("/admin")
    public String registerAdmin() {
        return "registerAdmin";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/user/registerCourse")
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
    public String home(Principal p, Model m) {
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        m.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/user/courses")
    public String courses(Model model) {
        List<TrainingSession> trainingSessions = trainingSessionRepo.findAll();
        model.addAttribute("trainingSessions", trainingSessions);
        return "courses";
    }

    @GetMapping("/user/enroll")
    public String enroll(Principal p,Model model) {
        List<TrainingSession> trainingSessions = trainingSessionRepo.findAll();
        List<TrainingSession> trainingSessionsToEnroll = new ArrayList<TrainingSession>();
        List<TrainingSession> trainingSessionsToUnenroll = new ArrayList<TrainingSession>();
        for(TrainingSession session:  trainingSessions ){
            boolean isEnrolled = false;
            for(Enrollment enrollment: session.getEnrollments()){
                if(enrollment.getUser().getEmail().equals(p.getName())) {
                    trainingSessionsToUnenroll.add(session);
                    isEnrolled = true;
                }
            }
            if(!isEnrolled){
                trainingSessionsToEnroll.add(session);
            }
        }
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("trainingSessionsToEnroll", trainingSessionsToEnroll);
        model.addAttribute("trainingSessionsToUnenroll", trainingSessionsToUnenroll);
        return "enroll";
    }


    @GetMapping("/user/enrollments")
    public String enrollments(Principal p,Model model) {
        List<TrainingSession> trainingSessions = trainingSessionRepo.findAll();
        List<TrainingSession> trainingSessionsEnrolled = new ArrayList<TrainingSession>();
        for(TrainingSession session:  trainingSessions ){
            for(Enrollment enrollment: session.getEnrollments()){
                if(enrollment.getUser().getEmail().equals(p.getName())) {
                    trainingSessionsEnrolled.add(session);
                }
            }
        }
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("trainingSessionsEnrolled", trainingSessionsEnrolled);
        return "enrollments";
    }

    @PostMapping("/user/add-enrollment")
    public String addEnrollment(@RequestParam Integer userId, @RequestParam Integer trainingSessionId){
        User user = userRepo.findById(userId).get();
        TrainingSession trainingSession = trainingSessionRepo.findById(trainingSessionId).get();
        Enrollment e = enrollmentService.saveEnrollment(new Enrollment(user,trainingSession));
        return "redirect:/user/enroll";
    }

    @PostMapping("/user/remove-enrollment")
    public String deleteEnrollment(@RequestParam Integer userId, @RequestParam Integer trainingSessionId){
        User user = userRepo.findById(userId).get();
        TrainingSession trainingSession = trainingSessionRepo.findById(trainingSessionId).get();
        //Add code to remove enrollment
        Enrollment e = enrollmentRepo.findByUserIdAndTrainingSessionId(userId, trainingSessionId);
        enrollmentRepo.delete(e);
        return "redirect:/user/enroll";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session) {
        //System.out.println(user);
        User user1 = userService.saveUser(user);

        if (user1 != null) {
            session.setAttribute("msg", "User Registered Successfully");
        } else {
            session.setAttribute("msg", "Something went wrong on server");
        }
        return "redirect:/register";
    }

    @PostMapping("/user/saveTrainingSession")
    public String saveTrainingSession(@ModelAttribute TrainingSession trainingSession, HttpSession session) {
        //System.out.println(user);
        TrainingSession trainingSession1 = trainingSessionService.saveTrainingSession(trainingSession);

        if (trainingSession1 != null) {
            session.setAttribute("msg", "registered successfully");
        } else {
            session.setAttribute("msg", "Something went wrong on server");
        }
        return "redirect:/user/courses";
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/signin";
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

