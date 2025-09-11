package com.springLataleLauncher.demo.aop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springLataleLauncher.demo.controllers.AuthApi;

@ControllerAdvice(assignableTypes = {AuthApi.class})
public class SecurityException extends RuntimeException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Handle general exceptions and return an appropriate error response
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An security error occurred: " + ex.getMessage());
    }
}
