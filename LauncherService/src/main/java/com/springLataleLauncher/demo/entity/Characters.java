package com.springLataleLauncher.demo.entity;

import com.springLataleLauncher.demo.vo.Classes;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "characters", schema = "latale")
public class Characters {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "character_id")
    private Long characterId;

    @Column(name = "character_name")
    private String characterName;

    @Column(name = "character_class")
    private Classes characterClass;

    @Column(name = "character_bio")
    private String bio;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_character", nullable = false, referencedColumnName = "user_id")
    private User user;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Classes getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(Classes characterClass) {
        this.characterClass = characterClass;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Characters that = (Characters) o;
        return Objects.equals(getCharacterId(), that.getCharacterId()) && Objects.equals(getCharacterName(), that.getCharacterName()) && Objects.equals(getCharacterClass(), that.getCharacterClass()) && Objects.equals(getBio(), that.getBio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacterId(), getCharacterName(), getCharacterClass(), getBio(), getUser());
    }

    @Override
    public String toString() {
        return "Characters{" +
                "characterId=" + characterId +
                ", characterName='" + characterName + '\'' +
                ", characterClass=" + characterClass +
                ", characterBio='" + bio + '\'' +
                ", user=" + user +
                '}';
    }


}
