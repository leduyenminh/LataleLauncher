package com.springLataleLauncher.demo.interfaces;

import com.springLataleLauncher.demo.vo.Classes;

import java.time.LocalDateTime;

public class CharacterResponse{

    private String id;
    private String characterName;
    private String bio;
    private Classes characterClass;

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
}
