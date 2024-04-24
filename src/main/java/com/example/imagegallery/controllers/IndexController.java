package com.example.imagegallery.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {

    @GetMapping("/")
    public String showHomePage() {
        return "Home";
    }
}
