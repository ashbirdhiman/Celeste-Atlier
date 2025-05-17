package com.celesteatelier.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


     @ExceptionHandler(IllegalArgumentException.class)
     public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
         return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
     }
}
