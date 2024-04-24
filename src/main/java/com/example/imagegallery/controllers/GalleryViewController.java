package com.example.imagegallery.controllers;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller

public class GalleryViewController {

    private  final GalleryService galleryService;

    @Autowired
    public  GalleryViewController(GalleryService galleryService){
        this.galleryService = galleryService;
    }

    @GetMapping("/{galleryId}")
    public String viewGallery(@PathVariable Long galleryId, Model model){
        Gallery gallery = galleryService.getGalleryById(galleryId);
        model.addAttribute("gallery", gallery);
        return "GalleryView";
    }

    @GetMapping("/gallery/{galleryId}/edit")
    public String editGallery(@PathVariable Long galleryId, Model model) {
        Gallery gallery = galleryService.getGalleryById(galleryId);
        model.addAttribute("gallery", gallery);
        return "editGallery";
    }

    @PostMapping("/gallery/{galleryId}/edit")
    public String updateGallery(@PathVariable Long galleryId, @ModelAttribute("gallery") Gallery gallery) {
        galleryService.updateGallery(galleryId, gallery.getTitle(), gallery.getDescription());
        return "redirect:/gallery/{galleryId}";
    }
}


