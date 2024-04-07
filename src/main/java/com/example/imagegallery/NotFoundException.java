package com.example.imagegallery;

public class NotFoundException extends RuntimeException {
    public NotFoundException (String message){
        super(message);
    }
}
