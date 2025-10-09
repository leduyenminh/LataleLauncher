package com.springLataleLauncher.demo.repository;

import com.springLataleLauncher.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Already provided: findById, findAll, deleteById, save
    // // Custom queries
    // UserDetails findByUsername(String username);
}

