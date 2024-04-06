package com.example.imagegallery.repositories;


import com.example.imagegallery.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByFilename (String text);

    List<Image> findByUserId(Long userId);




}
