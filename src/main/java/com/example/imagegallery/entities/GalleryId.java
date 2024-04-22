package com.example.imagegallery.entities;

import java.io.Serializable;
import java.util.Objects;

public class GalleryId implements Serializable {

    private Long galleryId;
    private Long userId;


    public GalleryId() {
    }

   public Long getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(Long galleryId) {
        this.galleryId= galleryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalleryId galleryId = (GalleryId) o;
        return Objects.equals(galleryId, galleryId.galleryId) &&
                Objects.equals(userId, galleryId.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(galleryId, userId);
    }

}
