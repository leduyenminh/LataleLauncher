package com.springLataleLauncher.demo.vo;

import java.util.List;
import java.util.Objects;

public class UserVO {
    private Long id;

    private String username;

    private String password;

    private String userInfo;

    private List<CharacterVO> characterVOs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CharacterVO> getCharacters() {
        return characterVOs;
    }

    public void setCharacters(List<CharacterVO> characterVOs) {
        this.characterVOs = characterVOs;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String userName){
        this.username= userName;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserVO user = (UserVO) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getUserInfo(), user.getUserInfo()) && Objects.equals(getCharacters(), user.getCharacters());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getUserInfo(), getCharacters());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userInfo='" + userInfo + '\'' +
                '}';
    }
}
