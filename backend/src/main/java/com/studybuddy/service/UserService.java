package com.studybuddy.service;
import com.studybuddy.repository.UserRepository;
import com.studybuddy.model.User;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //-------Signup logic --------
    public boolean registerUser(String email, String password) {
    
        Optional<User> userOpt = userRepository.findByEmail(email);

        if(userOpt.isPresent()) {
            return false;
        }
        else {
            User newuser = new User(email, password);
            userRepository.save(newuser);
            return true;
        }


    } //end signup logic


    //-------Login logic --------
    public boolean loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if(userOpt.isPresent()) {
            if(userOpt.get().getPassword().equals(password))  {
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    } //end login logic

    //-----Assign a course to a user-------
   public void setUserCourse(String email, String course)  {
     Optional<User> userOpt = userRepository.findByEmail(email);

        if(userOpt.isPresent()) {
            userOpt.get().setCourse(course);
        }


   } //end assignment


    //-----Find users by course-----
   public List<User> getUsersByCourse(String course) {
   
    List<User> allUsers = userRepository.findAll();
    List<User> specificUsers = new ArrayList<>();

    for(int i = 0; i < allUsers.size(); i++) {
        if(allUsers.get(i).getCourse() != null &&
    allUsers.get(i).getCourse().equals(course)) {
            specificUsers.add(allUsers.get(i));
        }
    }


    return specificUsers;



   } //end getUsersByCourse

   


    




} //end class