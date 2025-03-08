package com.example.voxachat;

public class ChatMessage {
    private String username;
    private String text;
    private String content;
    private boolean isSender;

    public ChatMessage(String username, String text) {
        this.username = username;
        this.text = text;
        this.content = content;
        this.isSender = isSender;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public String getContent() {
        return content;
    }

    public boolean isSender() {
        return isSender;
    }
}