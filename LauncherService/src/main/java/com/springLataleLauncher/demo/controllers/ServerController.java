package com.springLataleLauncher.demo.controllers;

/**
 * REST controller exposing server list and selection endpoints for the launcher UI.
 */

import java.util.List;

import com.springLataleLauncher.demo.DTO.ServerInfo;
import com.springLataleLauncher.demo.DTO.ServerSelectionResponse;
import com.springLataleLauncher.demo.DTO.ServerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servers")
public class ServerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ServerService serverService;

    /**
     * Returns the current list of servers for the launcher UI.
     */
    @GetMapping
    @Operation(summary = "Server Fetching API")
    @CircuitBreaker(name = "serverLauncherCircuit", fallbackMethod = "fallbackFetchServers")
    public ResponseEntity<List<ServerInfo>> fetchServers() {
        return ResponseEntity.ok(serverService.getAllServers());
    }

    /**
     * Selects a server by name, returning a status message for the client.
     */
    @PostMapping("/{serverName}/select")
    @Operation(summary = "Server Selection API")
    @CircuitBreaker(name = "serverLauncherCircuit", fallbackMethod = "fallbackSelectServer")
    public ResponseEntity<ServerSelectionResponse> selectServer(@Valid @PathVariable String serverName) {
        return ResponseEntity.ok(serverService.selectServer(serverName));
    }

    /**
     * Fallback for server list failures that returns configured defaults.
     */
    private ResponseEntity<List<ServerInfo>> fallbackFetchServers(Throwable t) {
        logger.error("⚠️ Server Service has error. Using fallback ", t);
        return ResponseEntity.ok(serverService.getFallbackServers());
    }

    /**
     * Fallback for server selection failures that queues the request.
     */
    private ResponseEntity<ServerSelectionResponse> fallbackSelectServer(String serverName, Throwable t) {
        logger.error("⚠️ Server Selection failed. Using fallback ", t);
        return ResponseEntity.ok(ServerSelectionResponse.fallback(serverName));
    }
}
