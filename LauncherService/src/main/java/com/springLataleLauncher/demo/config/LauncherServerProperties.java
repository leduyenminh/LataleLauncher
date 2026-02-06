package com.springLataleLauncher.demo.config;

/**
 * Configuration properties for default launcher server entries.
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "launcher.servers")
public class LauncherServerProperties {
    private List<ServerDefinition> defaults = new ArrayList<>();

    /**
     * Returns the configured default server definitions.
     */
    public List<ServerDefinition> getDefaults() {
        return defaults;
    }

    /**
     * Sets the configured default server definitions.
     */
    public void setDefaults(List<ServerDefinition> defaults) {
        this.defaults = defaults;
    }

    public static class ServerDefinition {
        private String name;
        private String status;
        private String population;
        private Integer ping;

        /**
         * Returns the default server name.
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the default server name.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Returns the default server status label.
         */
        public String getStatus() {
            return status;
        }

        /**
         * Sets the default server status label.
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * Returns the default server population label.
         */
        public String getPopulation() {
            return population;
        }

        /**
         * Sets the default server population label.
         */
        public void setPopulation(String population) {
            this.population = population;
        }

        /**
         * Returns the default server ping value.
         */
        public Integer getPing() {
            return ping;
        }

        /**
         * Sets the default server ping value.
         */
        public void setPing(Integer ping) {
            this.ping = ping;
        }
    }
}
