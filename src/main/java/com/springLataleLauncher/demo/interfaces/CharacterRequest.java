package com.springLataleLauncher.demo.interfaces;

import com.springLataleLauncher.demo.vo.Classes;

import java.time.LocalDateTime;

public class CharacterRequest {
    private String characterName;
    private String bio;
    private LocalDateTime createdAt;
    private Classes characterClass;

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

    public Classes getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(Classes characterClass) {
        this.characterClass = characterClass;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
// Getters and setters
}
