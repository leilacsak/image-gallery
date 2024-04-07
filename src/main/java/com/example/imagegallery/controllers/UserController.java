package com.example.imagegallery.controllers;


import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.entities.User;
import com.example.imagegallery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loginedUser = userService.loginUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(loginedUser);
    }

    @GetMapping ("/{userId}/galleries")
    public ResponseEntity<List<Gallery>> getUserGalleries(@PathVariable Long userId) {
        List<Gallery> galleries = userService.getUserGalleries(userId);
        return ResponseEntity.ok(galleries);
    }
}



