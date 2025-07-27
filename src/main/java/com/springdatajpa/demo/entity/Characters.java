package com.springdatajpa.demo.entity;

import com.springdatajpa.demo.vo.classes;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "characters", schema = "latale")
public class Characters {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long characterId;

    @Column(name = "characterName")
    private String characterName;

    @Column(name = "characterClass")
    private Enum<classes> characterClass;

    @Column(name = "characterBio")
    private String bio;

    @JoinColumn(name = "characterUser")
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

    public Enum<classes> getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(Enum<classes> characterClass) {
        this.characterClass = characterClass;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Characters that = (Characters) o;
        return Objects.equals(getCharacterId(), that.getCharacterId()) && Objects.equals(getCharacterName(), that.getCharacterName()) && Objects.equals(getCharacterClass(), that.getCharacterClass()) && Objects.equals(getBio(), that.getBio()) && Objects.equals(getUser(), that.getUser());
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
                ", bio='" + bio + '\'' +
                ", user=" + user +
                '}';
    }


}
