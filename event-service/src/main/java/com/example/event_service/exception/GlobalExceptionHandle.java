package com.example.event_service.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Exception occurred: " + e.getMessage());
        return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        logger.error("NotFoundException occurred: " + e.getMessage());
        return ResponseEntity.status(404).body("Not found error occurred: " + e.getMessage());
    }
}
