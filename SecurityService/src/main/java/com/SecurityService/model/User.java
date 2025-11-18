package com.SecurityService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

//import com.springLataleLauncher.demo.entity.Characters;

@Entity
@Table(name="users", schema = "latale")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "username", unique = true, nullable = false)
    @NotNull
    private String username;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Column(name = "userinfo")
    private String userInfo;

    @Column(name = "role", nullable = false)
    private String role;

//   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user",  orphanRemoval = true)
//   private List<Characters> characters ;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //  public List<Characters> getCharacters() {
    //      return characters;
    //  }

    //  public void setCharacters(List<Characters> characters) {
    //      this.characters = characters;
    //  }
		
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

    public String getEmail(){
      return this.email;
    }
    public void setEmail(String email){
      this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getUserInfo(), user.getUserInfo()) && Objects.equals(getRole(), user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getEmail(), getPassword(), getUserInfo());
    }

		@Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userInfo='" + userInfo + '\'' +
//                ", connectedServers=" + connectedServers +
                '}';
    }


}
