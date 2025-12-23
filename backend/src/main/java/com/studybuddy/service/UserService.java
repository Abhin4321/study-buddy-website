package com.studybuddy.service;

import com.studybuddy.model.User;
import com.studybuddy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection (BEST practice)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a new user
    public boolean registerUser(User user) {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            return false; // email already taken
        }

        userRepository.save(user);
        return true;
    }

    // Login logic
    public boolean loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return false;
        }

        return user.get().getPassword().equals(password);
    }

    // Get all users (for course matching later)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void addCourseToUser(String email, String course) {
    Optional<User> user = userRepository.findByEmail(email);
    user.ifPresent(u -> u.addCourse(course));
}

public List<User> getUsersByCourse(String course) {
    return userRepository.findAll().stream()
            .filter(user -> user.getCourses().contains(course))
            .collect(Collectors.toList());
}
}