package com.example.voxachat;

public class Chat {
    private String username;
    private String lastMessage;

    public Chat(String username, String lastMessage) {
        this.username = username;
        this.lastMessage = lastMessage;
    }

    public String getUsername() {
        return username;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}