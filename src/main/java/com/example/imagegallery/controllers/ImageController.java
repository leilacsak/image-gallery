package com.example.imagegallery.controllers;

import com.example.imagegallery.entities.Image;
import com.example.imagegallery.services.GalleryService;
import com.example.imagegallery.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/image")

public class ImageController {

    private final ImageService imageService;

    private final GalleryService galleryService;

    @Autowired
    public ImageController(ImageService imageService, GalleryService galleryService){
        this.imageService = imageService;
        this.galleryService = galleryService;
    }

    @GetMapping("/gallery/{galleryId}")
    public ResponseEntity<List<Image>> getImagesByGalleryId(@PathVariable Long galleryId) {
        List<Image> images = imageService.getImagesByGalleryId(galleryId);
        return ResponseEntity.ok(images);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam ("imageFile")MultipartFile imageFile,
                                              @RequestParam ("description") String description,
                                              @RequestParam("galleryId") Long galleryId)
    {
        try {
            byte[] imageData = imageFile.getBytes();
            Image addedImage = imageService.addImage(galleryId, imageFile.getOriginalFilename(), description, imageData);
            return ResponseEntity.ok("Image uploaded successfully! Image ID: " + addedImage.getImageId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<Image> updateImage(@PathVariable Long imageId, @RequestBody Image updatedImage) {
        Image image = imageService.updateImage(imageId, updatedImage.getDescription(), updatedImage.getFilename());
        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }
}
