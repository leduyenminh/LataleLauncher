package com.springdatajpa.demo.interfaces;

import java.time.LocalDateTime;

public class CharacterRequest {
    private String bio;
    private LocalDateTime createdAt;

    public String getBio() {
        return bio;
    }

    public void setTodoItem(String todoItem) {
        this.bio = todoItem;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
// Getters and setters
}
