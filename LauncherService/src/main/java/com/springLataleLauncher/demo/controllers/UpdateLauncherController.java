package com.springLataleLauncher.demo.controllers;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springLataleLauncher.demo.grpc.UpdateClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/launcher")
@Tag(name = "Launcher", description = "API for game launcher")
public class UpdateLauncherController {
    @Autowired
    UpdateClientService updateClientService;

    @PostMapping
	@Operation(summary = "Update Launcher")
	public ResponseEntity<HttpResponse> executeUpdate() {
		updateClientService.checkForUpdate(1);
		return ResponseEntity.ok().build();
	}
}
