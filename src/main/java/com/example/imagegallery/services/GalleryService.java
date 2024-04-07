package com.example.imagegallery.services;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.entities.User;
import com.example.imagegallery.repositories.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final  UserService userService;

    @Autowired
    public GalleryService(GalleryRepository galleryRepository, UserService userService){
        this.galleryRepository = galleryRepository;
        this.userService = userService;
    }

    public Gallery createGallery (Long userid, String title, String description) {

        User user = userService.findByUserId(userid);

        if (user == null) {
            throw new RuntimeException("User does not exist!");

        }

        Gallery gallery = new Gallery();
        gallery.setTitle(title);
        gallery.setDescription(description);
        gallery.setUserid(userid);

        return galleryRepository.save(gallery);

    }

}
