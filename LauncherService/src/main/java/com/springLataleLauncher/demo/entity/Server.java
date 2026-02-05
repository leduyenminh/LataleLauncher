package com.springLataleLauncher.demo.entity;

/**
 * JPA entity mapping for launcher server metadata.
 */

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

    /**
     * Creates an empty server entity for JPA.
     */
    public Server() {
    }

    /**
     * Creates a server entity with all persisted fields.
     */
    public Server(Long serverId, String serverName, String serverStatus, String serverPopulation, Integer serverPing) {
        this.serverId = serverId;
        this.serverName = serverName;
        this.serverStatus = serverStatus;
        this.serverPopulation = serverPopulation;
        this.serverPing = serverPing;
    }

    /**
     * Returns the server primary key.
     */
    public Long getServerId() {
        return serverId;
    }

    /**
     * Sets the server primary key.
     */
    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    /**
     * Returns the stored server name.
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets the stored server name.
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * Returns the stored server status.
     */
    public String getServerStatus() {
        return serverStatus;
    }

    /**
     * Sets the stored server status.
     */
    public void setServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }

    /**
     * Returns the stored population label.
     */
    public String getServerPopulation() {
        return serverPopulation;
    }

    /**
     * Sets the stored population label.
     */
    public void setServerPopulation(String serverPopulation) {
        this.serverPopulation = serverPopulation;
    }

    /**
     * Returns the stored ping measurement.
     */
    public Integer getServerPing() {
        return serverPing;
    }

    /**
     * Sets the stored ping measurement.
     */
    public void setServerPing(Integer serverPing) {
        this.serverPing = serverPing;
    }

}
