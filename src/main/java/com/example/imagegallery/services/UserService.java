package com.example.imagegallery.services;

import com.example.imagegallery.entities.User;
import com.example.imagegallery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(String email, String password){

        if(userRepository.findByEmail(email) != null) {
            throw new RuntimeException("This email address is already taken!");
        }

        User user= new User();
        user.setEmail(email);
        user.setPassword(password);

        return userRepository.save(user);

    }

    public User loginUser(String email, String passrowd){

        User user = userRepository.findByEmailAndPassword(email, passrowd);
        if (user == null) {
            throw new RuntimeException("Invalid email address or password!");
        }
        return user;

    }

    
}
