package com.springLataleLauncher.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "launcher.servers")
public class LauncherServerProperties {
    private List<ServerDefinition> defaults = new ArrayList<>();

    public List<ServerDefinition> getDefaults() {
        return defaults;
    }

    public void setDefaults(List<ServerDefinition> defaults) {
        this.defaults = defaults;
    }

    public static class ServerDefinition {
        private String name;
        private String status;
        private String population;
        private Integer ping;

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
}
