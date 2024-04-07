package com.example.imagegallery.services;

import com.example.imagegallery.NotFoundException;
import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.entities.User;
import com.example.imagegallery.repositories.GalleryRepository;
import com.example.imagegallery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final  UserRepository userRepository;

    @Autowired
    public GalleryService(GalleryRepository galleryRepository, UserRepository userRepository){
        this.galleryRepository = galleryRepository;
        this.userRepository = userRepository;
    }

    public Gallery createGallery (Long userid, String title, String description) {

        User user = userRepository.findByUserId(userid);

        if (user == null) {
            throw new RuntimeException("User does not exist!");
        }

        Gallery gallery = new Gallery();
        gallery.setTitle(title);
        gallery.setDescription(description);
        gallery.setUserid(userid);

        return galleryRepository.save(gallery);
    }

    public Gallery updateGallery(Long galleryid, String newTitle, String newDescription){
        Gallery gallery = galleryRepository.findById(galleryid)
                .orElseThrow(() -> new NotFoundException("Gallery not found!"));

        gallery.setDescription(newDescription);
        gallery.setTitle(newTitle);
        return galleryRepository.save(gallery);
    }

    public void deleteGallery(Long galleryid){
        Gallery gallery = galleryRepository.findById(galleryid)
                .orElseThrow(() -> new  NotFoundException("Gallery not found!"));
        galleryRepository.delete(gallery);
    }
}
