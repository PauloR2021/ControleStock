package com.paulo.controlstock.exceptions;

import com.paulo.controlstock.api.ResponseApiControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseApiControl<Void>> resourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseApiControl<>(false,ex.getMessage(), null,HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseApiControl<Void>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ResponseApiControl<>(false,ex.getMessage(),null,HttpStatus.METHOD_NOT_ALLOWED.value()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseApiControl<Void>> runtimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseApiControl<>(false,ex.getMessage(),null,HttpStatus.NOT_FOUND.value()));
    }
}
