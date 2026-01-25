package com.patientService.patientService.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {

    // Logger instance for logging warnings/errors
    private static final Logger log = LoggerFactory.getLogger(GlobalException.class);

    // Handle validation errors when @Valid DTO fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex) {
        // Map to store field -> error message
        Map<String, String> errors = new HashMap<>();

        // Collect all field errors and put them in map
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        // Return 400 Bad Request with error details
        return ResponseEntity.badRequest().body(errors);
    }

    // Handle custom EmailAlreadyExistException
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExistException(EmailAlreadyExistException ex){
        // Log warning with exception message
        log.warn("email already exists{}", ex.getMessage());

        // Map to hold custom error response
        Map<String,String> errors = new HashMap<>();
        errors.put("message","email already exists");

        // Return 400 Bad Request with custom message
        return ResponseEntity.badRequest().body(errors);
    }
    public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException ex){
        log.warn("Patient not fount{}",ex.getMessage());
        Map<String,String> errors=new HashMap<>();
        errors.put("message","Patient not found");
        return ResponseEntity.badRequest().body(errors);
    }

}
