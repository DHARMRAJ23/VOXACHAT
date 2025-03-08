package com.example.voxachat;
public class Message {
    private String content;
    private boolean isSender;
    private int getText; // true if the message is from the sender, false if from the receiver

    public Message(String content, boolean isSender) {
        this.content = content;
        this.isSender = isSender;
    }

    public String getContent() {
        return content; // Ensure this returns a String
    }

    public boolean isSender() {
        return isSender;
    }

    public int getText() {
        return getText;
    }
}