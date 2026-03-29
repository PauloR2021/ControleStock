package com.paulo.controlstock.exceptions;

import com.paulo.controlstock.api.ResponseApiControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseApiControl<Void>> resourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseApiControl<>(false,ex.getMessage(), null,HttpStatus.NOT_FOUND.value()));
    }
}
