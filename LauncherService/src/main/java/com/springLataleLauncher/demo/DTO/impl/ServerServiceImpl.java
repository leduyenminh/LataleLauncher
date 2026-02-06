package com.springLataleLauncher.demo.DTO.impl;

/**
 * Default server service implementation backed by the repository with config fallbacks.
 */

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springLataleLauncher.demo.DTO.ServerInfo;
import com.springLataleLauncher.demo.DTO.ServerSelectionResponse;
import com.springLataleLauncher.demo.DTO.ServerService;
import com.springLataleLauncher.demo.config.LauncherServerProperties;
import com.springLataleLauncher.demo.entity.Server;
import com.springLataleLauncher.demo.repository.ServerRepository;

@Service
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private final LauncherServerProperties launcherServerProperties;

    /**
     * Constructs the service with repository access and fallback server properties.
     */
    @Autowired
    public ServerServiceImpl(ServerRepository serverRepository, LauncherServerProperties launcherServerProperties) {
        this.serverRepository = serverRepository;
        this.launcherServerProperties = launcherServerProperties;
    }

    /**
     * Loads all servers from persistence, falling back to defaults when empty.
     */
    @Override
    public List<ServerInfo> getAllServers() {
        List<Server> servers = serverRepository.findAll();
        if (servers.isEmpty()) {
            return getFallbackServers();
        }

        return servers.stream()
                .map(this::mapServer)
                .collect(Collectors.toList());

    private final ServerRepository serverRepository;
    private final LauncherServerProperties launcherServerProperties;

    @Autowired
    public ServerServiceImpl(ServerRepository serverRepository, LauncherServerProperties launcherServerProperties) {
        this.serverRepository = serverRepository;
        this.launcherServerProperties = launcherServerProperties;
    }

    /**
     * Builds server info from configured fallback entries.
     */
    @Override
    public List<ServerInfo> getFallbackServers() {
        List<LauncherServerProperties.ServerDefinition> defaults = launcherServerProperties.getDefaults();
        if (defaults == null || defaults.isEmpty()) {
            return Collections.emptyList();
        }

        return defaults.stream()
                .map(definition -> new ServerInfo(
                        null,
                        definition.getName(),
                        Optional.ofNullable(definition.getStatus()).orElse("offline"),
                        Optional.ofNullable(definition.getPopulation()).orElse("Unknown"),
                        Optional.ofNullable(definition.getPing()).orElse(0)))
    public List<ServerInfo> getAllServers() {
        List<Server> servers = serverRepository.findAll();
        if (servers.isEmpty()) {
            return getFallbackServers();
        }

        return servers.stream()
                .map(this::mapServer)
                .collect(Collectors.toList());
    }

    /**
     * Validates a server name and returns a selection status response.
     */
    @Override
    public ServerSelectionResponse selectServer(String serverName) {
        boolean exists = serverRepository.existsByServerName(serverName);
        if (!exists) {
            return new ServerSelectionResponse(serverName, "unavailable", "Server is not available.");
        }
        return new ServerSelectionResponse(serverName, "selected", "Server selection confirmed.");
    }

    /**
     * Maps a server entity to the DTO returned to the client.
     */
    public List<ServerInfo> getFallbackServers() {
        List<LauncherServerProperties.ServerDefinition> defaults = launcherServerProperties.getDefaults();
        if (defaults == null || defaults.isEmpty()) {
            return Collections.emptyList();
        }

        return defaults.stream()
                .map(definition -> new ServerInfo(
                        null,
                        definition.getName(),
                        Optional.ofNullable(definition.getStatus()).orElse("offline"),
                        Optional.ofNullable(definition.getPopulation()).orElse("Unknown"),
                        Optional.ofNullable(definition.getPing()).orElse(0)))
                .collect(Collectors.toList());
    }

    @Override
    public ServerSelectionResponse selectServer(String serverName) {
        boolean exists = serverRepository.existsByServerName(serverName);
        if (!exists) {
            return new ServerSelectionResponse(serverName, "unavailable", "Server is not available.");
        }
        return new ServerSelectionResponse(serverName, "selected", "Server selection confirmed.");
    }

    private ServerInfo mapServer(Server server) {
        return new ServerInfo(
                server.getServerId(),
                server.getServerName(),
                Optional.ofNullable(server.getServerStatus()).orElse("offline"),
                Optional.ofNullable(server.getServerPopulation()).orElse("Unknown"),
                Optional.ofNullable(server.getServerPing()).orElse(0));
    }
}
