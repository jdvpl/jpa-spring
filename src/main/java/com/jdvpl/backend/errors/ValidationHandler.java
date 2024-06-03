package com.jdvpl.backend.errors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdvpl.backend.domain.Error;
import com.jdvpl.backend.domain.ErrorResponse;

@RestControllerAdvice
public class ValidationHandler {
    @ExceptionHandler
    protected ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            Error err = new Error();
            err.setField(error.getField());
            err.setError(error.getDefaultMessage());
            errors.add(err);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(400,"error de datos",new Date(),errors));
    }
    
    @ExceptionHandler
    protected ResponseEntity<Object> handleException(DataInvalidException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error());
    }
}
