package com.springLataleLauncher.demo.repository;

/**
 * Repository for launcher server persistence queries.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springLataleLauncher.demo.entity.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    /**
     * Checks whether a server with the given name exists.
     */
    boolean existsByServerName(String serverName);
}
