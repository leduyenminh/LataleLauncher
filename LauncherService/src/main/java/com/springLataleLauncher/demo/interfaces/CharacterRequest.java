package com.springLataleLauncher.demo.interfaces;

import com.springLataleLauncher.demo.vo.Classes;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class CharacterRequest {

    @NotBlank(message = "Name is required")
    private String characterName;

    private String bio;

    @NotBlank(message = "Class is required")
    private Classes characterClass;

    @NotBlank(message = "Username is required")
    private String username;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

// Getters and setters
}
