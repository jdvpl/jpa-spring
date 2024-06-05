package com.jdvpl.backend.errors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;



@RestControllerAdvice
public class ValidationHandler {
   @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> exceptionHandler(Exception exception,HttpServletRequest request){
        Map<String, String> apiError=new HashMap<>();
        apiError.put("message", exception.getLocalizedMessage());
        apiError.put("timestamp", new Date().toString());
        apiError.put("url", request.getRequestURL().toString());
        apiError.put("http-method", request.getMethod());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }
    public ResponseEntity<Map<String, String>> createErrorResponse(String message, HttpStatus status, HttpServletRequest request) {
        Map<String, String> apiError = new HashMap<>();
        apiError.put("message", message);
        apiError.put("timestamp", new Date().toString());
        apiError.put("url", request.getRequestURL().toString());
        apiError.put("http-method", request.getMethod());

        return ResponseEntity.status(status).body(apiError);
    }

    public static class CustomerError extends RuntimeException {
        public CustomerError(String message) {
            super(message);
        }
    }
}
