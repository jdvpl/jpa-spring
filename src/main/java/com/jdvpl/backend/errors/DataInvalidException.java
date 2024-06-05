package com.jdvpl.backend.errors;

import org.springframework.validation.BindingResult;

public class DataInvalidException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private int status;

    public DataInvalidException(int status, BindingResult bindingResult, String message) {
        super(message);
        this.status = status;
    }

}
