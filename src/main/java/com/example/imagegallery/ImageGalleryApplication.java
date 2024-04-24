package com.example.imagegallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.imagegallery"})
@EnableAutoConfiguration
public class ImageGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageGalleryApplication.class, args);
	}

}
