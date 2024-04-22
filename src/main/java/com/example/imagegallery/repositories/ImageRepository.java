package com.example.imagegallery.repositories;


import com.example.imagegallery.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(name = "Image.findByGalleryId")
    List<Image> findByGalleryId(Long galleryId);
    @Query("DELETE FROM Image i WHERE i.gallery.galleryId = :galleryId")
    void deleteByGalleryId(Long galleryId);
}
