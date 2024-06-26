package com.example.imagegallery.services;

import com.example.imagegallery.NotFoundException;
import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.entities.User;
import com.example.imagegallery.repositories.GalleryRepository;
import com.example.imagegallery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final  UserRepository userRepository;

    @Autowired
    public GalleryService(GalleryRepository galleryRepository, UserRepository userRepository){
        this.galleryRepository = galleryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Gallery createGallery (Long userId, String title, String description) {

        User user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new RuntimeException("User does not exist!");
        }

        Gallery gallery = new Gallery();
        gallery.setTitle(title);
        gallery.setDescription(description);
        gallery.setUserId(userId);

        return galleryRepository.save(gallery);
    }

    @Transactional
    public Gallery updateGallery(Long galleryId, String newTitle, String newDescription) {
        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new NotFoundException("Gallery not found!"));

        gallery.setDescription(newDescription);
        gallery.setTitle(newTitle);
        return galleryRepository.save(gallery);
    }

    public void deleteGallery(Long galleryId) {
        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new  NotFoundException("Gallery not found!"));
        galleryRepository.delete(gallery);
    }

    public Gallery getGalleryById(Long galleryId) {
        return galleryRepository.findById(galleryId)
                .orElseThrow(() -> new NotFoundException ("Gallery not found!"));
    }

    public List<Gallery> getAllGalleries() {
        return galleryRepository.findAll();
    }
}