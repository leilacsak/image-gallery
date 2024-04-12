package com.example.imagegallery.entities;

import java.io.Serializable;
import java.util.Objects;

public class GalleryId implements Serializable {

    private Long galleryid;
    private Long userid;


    public GalleryId() {
    }

   public Long getGalleryid() {
        return galleryid;
    }

    public void setGalleryid(Long galleryid) {
        this.galleryid = galleryid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalleryId galleryId = (GalleryId) o;
        return Objects.equals(galleryid, galleryId.galleryid) &&
                Objects.equals(userid, galleryId.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(galleryid, userid);
    }

}
