package com.example.imagegallery.controllers;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.entities.Image;
import com.example.imagegallery.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Gallery createdGallery = galleryService.createGallery(gallery.getUserid(), gallery.getTitle(), gallery.getDescription());
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

    @GetMapping("/gallery/{galleryId}")
    public String viewGallery(@PathVariable Long galleryId, Model model) {
        Gallery gallery = galleryService.getGalleryById(galleryId);
        List<Image> images = gallery.getImages();

        model.addAttribute("gallery", gallery);
        model.addAttribute("images", images);

        return "gallery";
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Gallery> galleries = galleryService.getAllGalleries();
        model.addAttribute("galleries", galleries);
        return "Home";
    }
}


