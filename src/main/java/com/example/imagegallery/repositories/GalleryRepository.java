package com.example.imagegallery.repositories;

import com.example.imagegallery.entities.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    Gallery findByTitle( String text);

    List<Gallery> findByUserId(Long userid);

}
