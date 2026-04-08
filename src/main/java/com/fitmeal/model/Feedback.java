package com.fitmeal.model;

import java.time.LocalDateTime;

public class Feedback {
    private static int idCounter = 1;
    private int id;
    private String userName;
    private String email;
    private String content;
    private int rating;
    private LocalDateTime createdAt;
    private String type; // "suggestion", "bug", "general"

    public Feedback(String userName, String email, String content, int rating, String type) {
        this.id = idCounter++;
        this.userName = userName;
        this.email = email;
        this.content = content;
        this.rating = rating;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public String getContent() { return content; }
    public int getRating() { return rating; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getType() { return type; }
}
