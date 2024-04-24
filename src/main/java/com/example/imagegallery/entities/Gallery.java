package com.example.imagegallery.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "galleries")
@NamedQuery(name = "Image.findByGalleryId",
            query = "select i from Image i where i.gallery.galleryId = :galleryId")

public class Gallery {
    @OneToMany(mappedBy = "gallery")
    private List<Image> images;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long galleryId;

    private String title;
    private String description;

    @Column(name = "userId")
    private Long userId;

    public Gallery() {
    }

    public Long getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(Long galleryId) {
        this.galleryId = galleryId;
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
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;}

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}






