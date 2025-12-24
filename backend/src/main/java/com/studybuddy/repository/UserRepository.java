package com.studybuddy.repository;


import com.studybuddy.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    //in memory storage will go here
    private final Map<String, User> users = new HashMap<>();

    
    // signup
    public void save(User user) {
        if (!users.containsKey(user.getEmail())) {
        users.put(user.getEmail(), user);
         }
    }

    //login
    public Optional<User> findByEmail(String email) {
        if(users.containsKey(email)) {
            return Optional.of(users.get(email));
        }
        else {
           return Optional.empty();
        }
}





    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }





}
