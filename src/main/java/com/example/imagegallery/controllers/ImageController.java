package com.example.imagegallery.controllers;

import com.example.imagegallery.entities.Image;
import com.example.imagegallery.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")

public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<Image> addImage(@RequestBody Image image) {
        Image addedImage = imageService.addImage(image.getGallery().getGalleryid(), image.getFilename(), image.getDescription());
        return ResponseEntity.ok(addedImage);
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<Image> updateImage(@PathVariable Long imageId, @RequestBody Image updatedImage) {
        Image image = imageService.updateImage(imageId, updatedImage.getDescription(), updatedImage.getFilename());
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageid) {
        imageService.deleteImage(imageid);
        return ResponseEntity.noContent().build();
    }



}
