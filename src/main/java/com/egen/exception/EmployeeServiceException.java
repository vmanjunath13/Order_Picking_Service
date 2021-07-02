package com.egen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EmployeeServiceException extends RuntimeException {

    public EmployeeServiceException(String message) {
        super(message);
    }
}
