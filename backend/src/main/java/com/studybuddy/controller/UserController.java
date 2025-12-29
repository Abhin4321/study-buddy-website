package com.studybuddy.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.studybuddy.service.UserService;

import jakarta.servlet.http.HttpSession;

import com.studybuddy.model.User;

import java.util.*;

@Controller
public class UserController {

private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}

//------------Signup Endpoint----------------
@PostMapping("/signup")
public String signup(@RequestParam String email, @RequestParam String password) {
    boolean value = userService.registerUser(email, password);

    if(value == true) {
        return "index";
    }
    else {
        return "signup";
    }

}

//-----------Login Endpoint-------------------
@PostMapping("/login")
public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {

    boolean value = userService.loginUser(email, password);

    if(value == true) {
        session.setAttribute("email", email);
        return "courses";
    }
    else {
        return "index";
    }

}


//----------Select Course Endpoint--------------
@PostMapping("/select-course")
public String selectCourse(@RequestParam String course, HttpSession session) {
    String email = (String) session.getAttribute("email");
    if(email != null) {
        userService.setUserCourse(email, course);
        return "redirect:/users?course=" + course;
    }
    return "redirect:/index";
}


//----------View Users Endpoint------------------
@GetMapping("/users")
public String viewUsers(@RequestParam String course, Model model, HttpSession session) {
    String email = (String) session.getAttribute("email");
    if(email == null) {
        return "redirect:/index";
    }
    
       model.addAttribute("users", userService.getUsersByCourse(course));
    
    return "users";
}

@GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate();  
    return "redirect:/index";  
}


} //end class