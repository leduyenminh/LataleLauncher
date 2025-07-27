package com.springdatajpa.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springdatajpa.demo.entity.Characters;

@ControllersAdvice
public class CharactersException extends RuntimeException {

		@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        // Handle general exceptions and return an appropriate error response
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred : " + ex.getMessage() + "with this character");
    }
}
