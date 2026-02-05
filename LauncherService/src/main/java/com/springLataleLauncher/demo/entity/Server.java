package com.springLataleLauncher.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

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

    @Column(name = "server_population")
    private String serverPopulation;

    @Column(name = "server_ping")
    private Integer serverPing;

    public Server() {
    }

    public Server(Long serverId, String serverName, String serverStatus, String serverPopulation, Integer serverPing) {
        this.serverId = serverId;
        this.serverName = serverName;
        this.serverStatus = serverStatus;
        this.serverPopulation = serverPopulation;
        this.serverPing = serverPing;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }

    public String getServerPopulation() {
        return serverPopulation;
    }

    public void setServerPopulation(String serverPopulation) {
        this.serverPopulation = serverPopulation;
    }

    public Integer getServerPing() {
        return serverPing;
    }

    public void setServerPing(Integer serverPing) {
        this.serverPing = serverPing;
    }

}
