package com.example.imagegallery.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Gallery {

    @Id
    @OneToMany(mappedBy = "Gallery", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Image>images;
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

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}



