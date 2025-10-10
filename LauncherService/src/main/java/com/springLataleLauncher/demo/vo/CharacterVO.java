package com.springLataleLauncher.demo.vo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

public class CharacterVO {
    private Long characterId;

    private String characterName;

    private Classes characterClass;

    private String bio;

    private UserVO user;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CharacterVO that = (CharacterVO) o;
        return Objects.equals(getCharacterId(), that.getCharacterId()) && Objects.equals(getCharacterName(), that.getCharacterName()) && getCharacterClass() == that.getCharacterClass() && Objects.equals(getBio(), that.getBio()) && Objects.equals(getUser(), that.getUser()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacterId(), getCharacterName(), getCharacterClass(), getBio());
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public Classes getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(Classes characterClass) {
        this.characterClass = characterClass;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CharacterVO{" +
                "characterId=" + characterId +
                ", characterName='" + characterName + '\'' +
                ", characterClass=" + characterClass +
                ", bio='" + bio + '\'' +
                '}';
    }
}
