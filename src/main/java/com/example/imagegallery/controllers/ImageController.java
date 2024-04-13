package com.example.imagegallery.controllers;

import com.example.imagegallery.entities.Image;
import com.example.imagegallery.services.GalleryService;
import com.example.imagegallery.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")

public class ImageController {

    private final ImageService imageService;



    @Autowired
    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @GetMapping("/gallery/{GalleryId}")
    public ResponseEntity<List<Image>> getImagesByGalleryId(@PathVariable Long GalleryId) {
        List<Image> images = imageService.getImagesByGalleryId(GalleryId);
        return ResponseEntity.ok(images);
    }

    @PostMapping
    public ResponseEntity<Image> addImage(@RequestBody Image image) {
        Image addedImage = imageService.addImage(image.getGallery().getGalleryId(),
                image.getFilename(), image.getDescription(), image.getImageData());
        return ResponseEntity.ok(addedImage);
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<Image> updateImage(@PathVariable Long ImageId, @RequestBody Image updatedImage) {
        Image image = imageService.updateImage(ImageId, updatedImage.getDescription(), updatedImage.getFilename());
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long ImageId) {
        imageService.deleteImage(ImageId);
        return ResponseEntity.noContent().build();
    }
}
