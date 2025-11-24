package com.springLataleLauncher.demo.controllers;

import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springLataleLauncher.demo.DTO.ServerService;
import com.springLataleLauncher.demo.interfaces.CharacterRequest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RequestMapping("/api/servers")
public class ServerController {
private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ServerService serverService;

    @GetMapping
	@Operation(summary = "Server Fetching API")
	@CircuitBreaker(name = "serverLauncherCircuit", fallbackMethod = "fallbackServerLauncherCheck")
	public ResponseEntity<HttpResponse> fetchServers() {
		serverService.getAllServer();
		return ResponseEntity.ok().build();
	}

    @PostMapping("/{serverName}/select")
	@Operation(summary = "Server Selection API")
	@CircuitBreaker(name = "serverLauncherCircuit", fallbackMethod = "fallbackServerLauncherCheck")
	public ResponseEntity<HttpResponse> selectServer(@Valid @PathVariable String serverName) {
		serverService.loginServer(serverName);
		return ResponseEntity.ok().build();
	}

	private String fallbackServerLauncherCheck(Throwable t) {
        logger.error("⚠️ Server Service has error. Using fallback ", t);
        return t.getMessage();
    }
}
