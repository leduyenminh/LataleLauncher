package com.SecurityService.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {AuthController.class})
public class AuthException extends RuntimeException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Handle general exceptions and return an appropriate error response
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An security error occurred: " + ex.getMessage());
    }
}
