package com.example.imagegallery.controllers;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class GalleryViewController {

    private  final GalleryService galleryService;

    @Autowired
    public  GalleryViewController(GalleryService galleryService){
        this.galleryService = galleryService;
    }

    @GetMapping("/{galleryid}")
    public String viewGallery(@PathVariable Long galleryid, Model model){
        Gallery gallery = galleryService.getGalleryById(galleryid);
        model.addAttribute("gallery", gallery);
        return "GalleryView";
    }

    @GetMapping("/gallery/{galleryid}/edit")
    public String editGallery(@PathVariable Long galleryId, Model model) {
        Gallery gallery = galleryService.getGalleryById(galleryId);
        model.addAttribute("gallery", gallery);
        return "editGallery";
    }

    @PostMapping("/gallery/{galleryid}/edit")
    public String updateGallery(@PathVariable Long galleryid, @ModelAttribute("gallery") Gallery gallery) {
        galleryService.updateGallery(galleryid, gallery.getTitle(), gallery.getDescription());
        return "redirect:/gallery/{galleryid}";
    }
}


