package com.springLataleLauncher.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springLataleLauncher.demo.entity.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    boolean existsByServerName(String serverName);
}
