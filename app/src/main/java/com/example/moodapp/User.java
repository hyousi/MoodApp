package com.example.moodapp;

public class User {

    private String email;
    private int theme;

    public User(){

    }

    public User(String email, int theme) {
        this.email = email;
        this.theme = theme;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }
}
