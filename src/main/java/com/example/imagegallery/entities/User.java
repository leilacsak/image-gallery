package com.example.imagegallery.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long userId;
    private String email;
    private String password;

    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Gallery> galleries;

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
            return password;}

    public void setPassword(String password){
        this.password = password;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public User(){

    }
}

