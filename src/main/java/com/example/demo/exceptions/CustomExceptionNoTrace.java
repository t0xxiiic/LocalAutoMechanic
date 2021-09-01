package com.example.demo.exceptions;

/**
 * Custom exception with no stacktrace
 */
public class CustomExceptionNoTrace extends RuntimeException{
    public CustomExceptionNoTrace(String message, Throwable throwable) {
        super(message, throwable, true, false);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
