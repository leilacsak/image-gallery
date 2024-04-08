package com.example.imagegallery.services;

import com.example.imagegallery.entities.Gallery;
import com.example.imagegallery.entities.Image;
import com.example.imagegallery.repositories.GalleryRepository;
import com.example.imagegallery.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.imagegallery.NotFoundException;

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

    public List<Image> findByGalleryId(Long galleryid) {
        return imageRepository.findByGalleryId(galleryid);
    }

    public void deleteImagesByGalleryId(Long galleryid) {
        imageRepository.deleteByGalleryId(galleryid);
    }

    public Image addImage(Long galleryid, String filename, String description, byte[] imageData) {
        Gallery gallery = galleryRepository.findById(galleryid)
                .orElseThrow(() -> new NotFoundException("Gallery is not found!"));


        Image image = new Image();
        image.setGallery(gallery);
        image.setDescription(description);
        image.setFilename(filename);
        image.setImageData(imageData);
        imageRepository.save(image);

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
            BufferedImage originalImage = ImageIO.read(inputStream);

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

            } else {
                throw new IOException("Could not load original image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occured while generating thumbnail..." + e.getMessage());
        }

        return image;
    }


    public Image updateImage(Long imageid, String filename, String description) {
        Image image = imageRepository.findById(imageid)
                .orElseThrow(() -> new NotFoundException("Image not found!"));

        image.setFilename(filename);
        image.setDescription(description);
        return imageRepository.save(image);
    }

    public void deleteImage (Long imageid) {
        Image image = imageRepository.findById(imageid)
                .orElseThrow(() -> new NotFoundException("Image not found!"));

        imageRepository.delete(image);
    }
}









