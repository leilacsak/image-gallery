package com.example.imagegallery.controllers;


import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.entities.User;
import com.example.imagegallery.repositories.UserRepository;
import com.example.imagegallery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            return ResponseEntity.badRequest().build();
        }
        User registeredUser = userService.registerUser(user.getEmail(), user.getPassword(), user.getUserName());
        return ResponseEntity.ok(registeredUser);
    }


    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loginedUser = userService.loginUser(user.getEmail(), user.getPassword());
        if (loginedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(loginedUser);
    }

    @GetMapping ("/{userId}/gallery")
    public ResponseEntity<List<Gallery>> getUserGalleries(@PathVariable Long userId) {
        List<Gallery> galleries = userService.getUserGalleries(userId);
        return ResponseEntity.ok(galleries);
    }
}



