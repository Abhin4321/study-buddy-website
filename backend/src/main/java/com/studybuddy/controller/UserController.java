package com.studybuddy.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.studybuddy.service.UserService;

import jakarta.servlet.http.HttpSession;

//import com.studybuddy.model.User;

//import java.util.*;

@Controller
public class UserController {


private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}

   // ---------- PAGE ROUTES (GET) ----------

    @GetMapping("/")
    public String showLoginPage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "index";
    }

    @GetMapping("/signup")
    public String showSignup() {
        return "signup";
    }

    @GetMapping("/courses")
    public String showCourses(HttpSession session) {
        if (session.getAttribute("email") == null) {
            return "redirect:/login";
        }
        return "courses";
    }



//------------ENDPOINTS---------------------


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
         return "redirect:/courses";
    }
    else {
        return "index";
    }

}


//----------Select Course Endpoint--------------
@PostMapping("/connect")
public String selectCourse(@RequestParam String courses, HttpSession session) {
    String email = (String) session.getAttribute("email");
    if(email != null) {
        userService.setUserCourse(email, courses);
        return "redirect:/users?courses=" + courses;
    }
    return "redirect:/login";
}


//----------View Users Endpoint------------------
@GetMapping("/users")
public String viewUsers(@RequestParam String courses, Model model, HttpSession session) {
    String email = (String) session.getAttribute("email");
    if(email == null) {
         return "redirect:/login";
    }
    
       model.addAttribute("users", userService.getUsersByCourse(courses));
       model.addAttribute("courses", courses);

    
    return "users";
}

@GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate();  
    return "redirect:/login";
}


} //end class