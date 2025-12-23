package com.studybuddy.repository;

import com.studybuddy.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final Map<String, User> userStorage = new HashMap<>();

    // Save a new user or update existing
    public void save(User user) {
        userStorage.put(user.getEmail(), user);
    }

    // Find user by email
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userStorage.get(email));
    }

    // Return all users
    public List<User> findAll() {
        return new ArrayList<>(userStorage.values());
    }
}