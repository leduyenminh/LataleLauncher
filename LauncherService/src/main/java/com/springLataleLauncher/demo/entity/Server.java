package com.springLataleLauncher.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "server", schema = "latale")
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "server_id")
    private Long serverId;

    @Column(name = "server_name", unique = true)
    @NotNull
    private String serverName;

    @Column(name = "server_status")
    @NotNull
    private String serverStatus;

    

}
