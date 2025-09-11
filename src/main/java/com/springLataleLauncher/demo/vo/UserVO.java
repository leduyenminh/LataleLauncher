package com.springLataleLauncher.demo.vo;

import java.util.List;
import java.util.Objects;

public class UserVO {
    private Long id;

    private String username;

    private String password;

    private String userInfo;

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

    // public List<Character> getCharacters() {
    //     return characters;
    // }

    // public void setCharacters(List<Character> characters) {
    //     this.characters = characters;
    // }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public List<String> getConnectedServers() {
        return connectedServers;
    }

    public void setConnectedServers(List<String> connectedServers) {
        this.connectedServers = connectedServers;
    }

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "characterId", referencedColumnName = "characterId")
    // private List<Character> characters ;

    private List<String> connectedServers;

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
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getUserInfo(), user.getUserInfo()) && Objects.equals(getConnectedServers(), user.getConnectedServers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getUserInfo(), getConnectedServers());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", connectedServers=" + connectedServers +
                '}';
    }
}
