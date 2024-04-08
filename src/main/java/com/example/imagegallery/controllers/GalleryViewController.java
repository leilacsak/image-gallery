package com.example.imagegallery.controllers;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class GalleryViewController {

    private  final GalleryService galleryService;

    @Autowired
    public  GalleryViewController(GalleryService galleryService){
        this.galleryService = galleryService;
    }

    @GetMapping("/{galleryId}")
    public String viewGallery(@PathVariable Long galleryid, Model model){
        Gallery gallery = galleryService.getGalleryById(galleryid);
        model.addAttribute("gallery", gallery);
        return "GalleryView";
    }

}
