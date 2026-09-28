package com.example.DonateHub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Resource not found
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNotFound(
          ResourceNotFoundException ex) {

    Map<String, String> response = new HashMap<>();

    response.put("error", ex.getMessage());

    return new ResponseEntity<>(
            response,
            HttpStatus.NOT_FOUND
    );
  }

  // Business rule violation
  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<Map<String, String>> handleBusinessRule(
          BusinessRuleException ex) {

    Map<String, String> response = new HashMap<>();

    response.put("error", ex.getMessage());

    return new ResponseEntity<>(
            response,
            HttpStatus.BAD_REQUEST
    );
  }

  // General exception
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGeneralException(
          Exception ex) {

    Map<String, String> response = new HashMap<>();

    response.put("error", ex.getMessage());

    return new ResponseEntity<>(
            response,
            HttpStatus.INTERNAL_SERVER_ERROR
    );
  }
}