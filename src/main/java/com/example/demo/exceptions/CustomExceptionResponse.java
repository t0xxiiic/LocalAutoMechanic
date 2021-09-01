package com.example.demo.exceptions;

import lombok.Data;

@Data
public class CustomExceptionResponse {
    private final String message;
    private final int status;
    private final String error;
}
