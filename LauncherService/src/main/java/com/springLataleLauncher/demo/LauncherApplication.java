package com.springLataleLauncher.demo;

/**
 * Launcher service entry point with repository scanning and configuration wiring.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.springLataleLauncher.demo.config.LauncherServerProperties;

@SpringBootApplication
@EnableJpaRepositories("com.springLataleLauncher.demo.repository")
@EntityScan({"com.springLataleLauncher.demo.entity", "com.SecurityService.entity"})
@EnableConfigurationProperties(LauncherServerProperties.class)
@EnableCaching
@EnableAsync
public class LauncherApplication {
	/**
	 * Boots the Spring application context for the launcher service.
	 */
	public static void main(String[] args) {
		SpringApplication.run(LauncherApplication.class, args);
	}

}
