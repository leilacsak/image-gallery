package com.example.imagegallery.services;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.entities.User;
import com.example.imagegallery.repositories.GalleryRepository;
import com.example.imagegallery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final GalleryRepository galleryRepository;


    @Autowired
    public UserService (UserRepository userRepository, GalleryRepository galleryRepository){
        this.userRepository = userRepository;
        this.galleryRepository=galleryRepository;
    }


    public User registerUser(String email, String password, String userName){

        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("This email address is already taken!");
        }

        User user= new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUserName(userName);

        return userRepository.save(user);

    }

    public User loginUser(String email, String password){

        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new RuntimeException("Invalid email address or password!");
        }
        return user;
    }

    public List<Gallery> getUserGalleries(Long userId) {
        return galleryRepository.findByUserId(userId);
    }
}
