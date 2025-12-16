package com.tododemo.model;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class Todo {
    private int id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    private boolean isCompleted;
    
    private LocalDateTime createdAt;

    public Todo() {
        this.createdAt = LocalDateTime.now();
        this.isCompleted = false;
    }

    public Todo(int id, String title, String description, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
