package com.example.demo.controllers;

import com.example.demo.exceptions.CustomExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final HttpStatus INTERNAL_SERVER_ERROR_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final HttpStatus BAD_REQUEST_STATUS           = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<CustomExceptionResponse> oops(NullPointerException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new CustomExceptionResponse(
                        "Something went wrong...",
                        INTERNAL_SERVER_ERROR_STATUS.value(),
                        INTERNAL_SERVER_ERROR_STATUS.name()
                ),
                INTERNAL_SERVER_ERROR_STATUS
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomExceptionResponse> handleIllegalArgException(IllegalArgumentException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new CustomExceptionResponse(
                        exception.getMessage(),
                        BAD_REQUEST_STATUS.value(),
                        BAD_REQUEST_STATUS.name()
                ),
                BAD_REQUEST_STATUS
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomExceptionResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new CustomExceptionResponse(
                        exception.getMessage(),
                        BAD_REQUEST_STATUS.value(),
                        BAD_REQUEST_STATUS.name()
                ),
                BAD_REQUEST_STATUS
        );
    }
}
