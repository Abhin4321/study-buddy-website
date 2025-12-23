package com.studybuddy.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private String email;
    private String password;
    private Set<String> courses = new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getCourses() {
        return courses;
    }

    // add course
    public void addCourse(String course) {
        courses.add(course);
    }
}