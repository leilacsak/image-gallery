package com.example.imagegallery.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long galleryid;

    private String title;
    private String description;
    private Long userid;

    public Gallery() {
    }

    public Long getGalleryid() {
        return galleryid;
    }

    public void setGalleryid(Long galleryid) {
        this.galleryid = galleryid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userId) {
        this.userid = userid;
    }
}



