package com.springLataleLauncher.demo.controllers;

import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springLataleLauncher.demo.grpc.UpdateClientService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/launcher")
@Tag(name = "Launcher", description = "API for game launcher")
public class UpdateLauncherController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UpdateClientService updateClientService;

    @PostMapping
	@Operation(summary = "Update Launcher")
	@CircuitBreaker(name = "updateLauncherCircuit", fallbackMethod = "fallbackUpdateLauncherCheck")
	public ResponseEntity<HttpResponse> executeUpdate() {
		updateClientService.checkForUpdate(1);
		return ResponseEntity.ok().build();
	}

	private String fallbackUpdateLauncherCheck(Throwable t) {
        logger.error("⚠️ Update Launcher service unavailable. Using fallback ", t);
        return t.getMessage();
    }
}
