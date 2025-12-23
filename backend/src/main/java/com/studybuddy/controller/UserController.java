package com.studybuddy.controller;

import com.studybuddy.model.User;
import com.studybuddy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ---------- LOGIN ----------
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        boolean success = userService.loginUser(email, password);

        return success ? "redirect:/courses.html?email=" + email : "redirect:/login.html";
    }

    // ---------- SIGNUP ----------
    @PostMapping("/signup")
    public String signup(@RequestParam String email,
                         @RequestParam String password) {

        User newUser = new User(email, password);
        boolean success = userService.registerUser(newUser);

        // Redirect to login page regardless for now
        // You can improve this later by showing error on failure
        return "redirect:/login.html";
    }

    // ---------- CONNECT COURSE ----------
    @PostMapping("/connect")
    public String connect(@RequestParam String email,
                          @RequestParam String courses, Model model) {

        // Add selected course to user
        userService.addCourseToUser(email, courses);

        // Get users enrolled in the course
        model.addAttribute(
                "users",
                userService.getUsersByCourse(courses)
        );

        return "users";  // View to display users connected to the course
    }
}