package com.jdvpl.backend.errors;

import lombok.Data;

@Data
public class GeneralException extends RuntimeException {
    private String description;
    private int status;
    private String message;
    private String exceptionClass;
    private int line;

    public GeneralException(String description, int status, String message, String exceptionClass) {
        super(message);
        this.description = description;
        this.status = status;
        this.message = message;
        this.exceptionClass = exceptionClass;
        this.line = getLineNumber();
    }


    private int getLineNumber() {
        return Thread.currentThread().getStackTrace()[3].getLineNumber();
    }

}
