package com.springdatajpa.demo.entity;

import javax.persistence.*;

import java.util.List;
@Entity
@Table(name="users", schema = "latale")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "userInfo")
    private String userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "characterId", referencedColumnName = "characterId")
    private List<Character> characters ;

    public String getUsername(){
      return this.username;
    }

    public void setUsername(String userName){
      this.username= userName;
    }



}
