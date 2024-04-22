package com.example.imagegallery.services;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.entities.Image;
import com.example.imagegallery.repositories.GalleryRepository;
import com.example.imagegallery.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.imagegallery.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public List<Image> getImagesByGalleryId(Long galleryId) {
        return imageRepository.findByGalleryId(galleryId);

    }

    public void deleteImagesByGalleryId(Long galleryId) {
        imageRepository.deleteByGalleryId(galleryId);
    }

    @Transactional
    public Image addImage(Long galleryId, String fileName, String description, byte[] imageData) {
        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new NotFoundException("Gallery is not found!"));
        try {
            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageData));

            if (originalImage != null) {
                int thumbnailWidth = 100;
                int thumbnailHeight = 100;
                BufferedImage thumbnail = new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = thumbnail.createGraphics();
                graphics2D.drawImage(originalImage, 0, 0, thumbnailWidth, thumbnailHeight, null);
                graphics2D.dispose();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(thumbnail, "jpg", baos);
                byte[] thumbnailData = baos.toByteArray();

                Image image = new Image();
                image.setGallery(gallery);
                image.setDescription(description);
                image.setFilename(fileName);
                image.setImageData(imageData);
                image.setThumbnailData(thumbnailData);
                return imageRepository.save(image);
            } else {
                throw new IOException("Could not load original image.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while generating thumbnail..." + e.getMessage());
        }
    }

    @Transactional
    public Image updateImage(Long imageId, String fileName, String description) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Image not found!"));

        image.setFilename(fileName);
        image.setDescription(description);
        return imageRepository.save(image);
    }
    @Transactional
    public void deleteImage (Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Image not found!"));

        imageRepository.delete(image);
    }
}









