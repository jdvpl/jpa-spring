package com.jdvpl.backend.domain;

import java.util.Date;
import java.util.List;

import com.jdvpl.backend.domain.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private Date date;
    private List<Error> errors;
}
