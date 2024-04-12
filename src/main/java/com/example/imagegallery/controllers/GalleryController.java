package com.example.imagegallery.controllers;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String createGallery(@ModelAttribute Gallery gallery) {
        galleryService.createGallery(gallery.getUserid(), gallery.getTitle(), gallery.getDescription());
        return "redirect:/";
    }

    @PutMapping("/{galleryid}")
    public String updateGallery(@PathVariable Long galleryid, @ModelAttribute Gallery gallery) {
        galleryService.updateGallery(galleryid, gallery.getTitle(), gallery.getDescription());
        return "redirect:/galleries/" + galleryid;
    }

    @DeleteMapping("/{galleryid}")
    public String deleteGallery(@PathVariable Long galleryid) {
        galleryService.deleteGallery(galleryid);
        return "redirect:/galleries";
    }

    @GetMapping("/{galleryid}")
    public String viewGallery(@PathVariable Long galleryid, Model model) {
        Gallery gallery = galleryService.getGalleryById(galleryid);
        model.addAttribute("gallery", gallery);
        return "GalleryView";
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Gallery> galleries = galleryService.getAllGalleries();
        model.addAttribute("galleries", galleries);
        return "Home";
    }

}


