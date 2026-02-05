package com.springLataleLauncher.demo.DTO;

public class ServerInfo {
    private Long id;
    private String name;
    private String status;
    private String population;
    private Integer ping;

    public ServerInfo() {
    }

    public ServerInfo(Long id, String name, String status, String population, Integer ping) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.population = population;
        this.ping = ping;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public Integer getPing() {
        return ping;
    }

    public void setPing(Integer ping) {
        this.ping = ping;
    }
}
