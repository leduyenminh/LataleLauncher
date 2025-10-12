package com.springLataleLauncher.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users", schema = "latale")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "userinfo")
    private String userInfo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user",  orphanRemoval = true)
    private List<Characters> characters ;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     public List<Characters> getCharacters() {
         return characters;
     }

     public void setCharacters(List<Characters> characters) {
         this.characters = characters;
     }
		
    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

//    public List<String> getConnectedServers() {
//        return connectedServers;
//    }
//
//    public void setConnectedServers(List<String> connectedServers) {
//        this.connectedServers = connectedServers;
//    }



//    @ElementCollection
//    @CollectionTable(
//            name = "user_connected_servers",
//            schema = "latale",
//            joinColumns = @JoinColumn(name = "user_id")
//    )
//    @Column(name = "server_name")
//    private List<String> connectedServers;

    public String getUsername(){
      return this.username;
    }
    public void setUsername(String userName){
      this.username= userName;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getUserInfo(), user.getUserInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getPassword(), getUserInfo(), getCharacters());
    }

		@Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userInfo='" + userInfo + '\'' +
//                ", connectedServers=" + connectedServers +
                '}';
    }


}
