package com.springLataleLauncher.demo.DTO;

/**
 * DTO describing server data presented to the launcher UI.
 */

public class ServerInfo {
    private Long id;
    private String name;
    private String status;
    private String population;
    private Integer ping;

    /**
     * Creates an empty server info payload for serializers.
     */
    public ServerInfo() {
    }

    /**
     * Creates a server info payload with all fields populated.
     */
    public ServerInfo() {
    }

    public ServerInfo(Long id, String name, String status, String population, Integer ping) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.population = population;
        this.ping = ping;
    }

    /**
     * Returns the server identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the server identifier.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the server display name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the server display name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the server status label.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the server status label.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the population label for the server.
     */
    public String getPopulation() {
        return population;
    }

    /**
     * Sets the population label for the server.
     */
    public void setPopulation(String population) {
        this.population = population;
    }

    /**
     * Returns the ping measurement in milliseconds.
     */
    public Integer getPing() {
        return ping;
    }

    /**
     * Sets the ping measurement in milliseconds.
     */
    public void setPing(Integer ping) {
        this.ping = ping;
    }
}
