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

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam ("imageFile")MultipartFile imageFile,
                                              @RequestParam ("description") String Description,
                                              @RequestParam("GalleryId") Long GalleryId)
    {
        try {
            byte[] imageData = imageFile.getBytes();
            Image addedImage = imageService.addImage(GalleryId, imageFile.getOriginalFilename(), Description, imageData);
            return ResponseEntity.ok("Image uploaded successfully! Image ID: " + addedImage.getImageId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
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
