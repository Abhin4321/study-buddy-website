package com.studybuddy.model;

/**
 * Represents a user of the Study Buddy application.
 * 
 * A user:
 * - Has an email and password
 * - Can select AT MOST ONE course at a time
 * - Can change their selected course at any time
 * 
 * No database integration is used yet.
 */

public class User {

    private String email;
    private String password; 
    private String course;

 
     /**
     * Creates a new user with an email and password.
     * The course is initially unset.
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.course = null;
    }

      // ---------- Getters ----------
    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCourse() {
        return this.course;
    }

     // ---------- Course Selection ----------

    /**
     * Sets or changes the user's selected course.
     * Selecting a new course replaces the previous one.
     */

    public void setCourse(String course) {
        this.course = course;
    }

} //end class