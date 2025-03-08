package com.example.voxachat;

public class Story {
    private String username;
    private String imageUrl;

    public Story(String username, String imageUrl) {
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}