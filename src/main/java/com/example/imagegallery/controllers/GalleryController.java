package com.example.imagegallery.controllers;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/gallery")
public class GalleryController {
    private final GalleryService galleryService;

    @Autowired
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @PostMapping
    public String createGallery(@ModelAttribute Gallery gallery) {
        galleryService.createGallery(gallery.getUserId(), gallery.getTitle(), gallery.getDescription());
        return "redirect:/";
    }

    @PutMapping("/{galleryId}")
    public String updateGallery(@PathVariable Long galleryId, @ModelAttribute Gallery gallery) {
        galleryService.updateGallery(galleryId, gallery.getTitle(), gallery.getDescription());
        return "redirect:/gallery/" + galleryId;
    }

    @DeleteMapping("/{galleryId}")
    public String deleteGallery(@PathVariable Long galleryId) {
        galleryService.deleteGallery(galleryId);
        return "redirect:/gallery";
    }

    @GetMapping("/{galleryId}")
    public String viewGallery(@PathVariable Long galleryId, Model model) {
        Gallery gallery = galleryService.getGalleryById(galleryId);
        model.addAttribute("gallery", gallery);
        return "GalleryView";
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Gallery> gallery = galleryService.getAllGalleries();
        model.addAttribute("gallery", gallery);
        return "Home";
    }

}


