package com.example.imagegallery.services;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.entities.Image;
import com.example.imagegallery.repositories.GalleryRepository;
import com.example.imagegallery.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.imagegallery.NotFoundException;

import java.util.List;

@Component
public class ImageService {

    private final ImageRepository imageRepository;
    private final GalleryRepository galleryRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, GalleryRepository galleryRepository) {
        this.imageRepository = imageRepository;
        this.galleryRepository = galleryRepository;
    }

    public List<Image> findByGalleryId(Long galleryid) {
        return imageRepository.findByGalleryId(galleryid);
    }

    public void deleteImagesByGalleryId(Long galleryid) {
        imageRepository.deleteByGalleryId(galleryid);
    }

    public Image addImage(Long galleryid, String filename, String description) {
        Gallery gallery = galleryRepository.findById(galleryid)
                .orElseThrow(() -> new NotFoundException("Gallery is not found!"));


        Image image = new Image();
        image.setGallery(gallery);
        image.setDescription(description);
        image.setFilename(filename);
        return imageRepository.save(image);
    }

    public Image updateImage(Long imageid, String filename, String description) {
        Image image = imageRepository.findById(imageid)
                .orElseThrow(() -> new NotFoundException("Image not found!"));


        image.setDescription(description);
        image.setFilename(filename);
        return imageRepository.save(image);
    }

    public void deleteImage (Long imageid) {
        Image image = imageRepository.findById(imageid)
                .orElseThrow(() -> new NotFoundException("Image not found!"));

        imageRepository.delete(image);
    }
}









