package com.example.imagegallery.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {

    @GetMapping("/")
    public String index(){
        return "Home";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "Login";
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "Register";
    }
}
