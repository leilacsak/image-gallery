package com.example.imagegallery.controllers;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/galleries")
public class GalleryController {
    private final GalleryService galleryService;

    @Autowired
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @PostMapping
    public ResponseEntity<Gallery> createGallery(@RequestBody Gallery gallery) {
        Gallery createdGallery = galleryService.createGallery(gallery.getUserid(),gallery.getTitle() , gallery.getDescription());
        return ResponseEntity.ok(createdGallery);
        }

    @PutMapping("/{galleryId}")
    public ResponseEntity<Gallery> updateGallery(@PathVariable Long galleryId, @RequestBody Gallery gallery) {
        Gallery updatedGallery = galleryService.updateGallery(galleryId, gallery.getTitle(), gallery.getDescription());
        return ResponseEntity.ok(updatedGallery);
        }

    @DeleteMapping("/{galleryId}")
    public ResponseEntity<Void> deleteGallery(@PathVariable Long galleryId) {
        galleryService.deleteGallery(galleryId);
        return ResponseEntity.noContent().build();
        }
}


