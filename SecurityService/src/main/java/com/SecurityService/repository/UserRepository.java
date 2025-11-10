package com.SecurityService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springLataleLauncher.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Already provided: findById, findAll, deleteById, save
    // // Custom queries
    // UserDetails findByUsername(String username);
}

