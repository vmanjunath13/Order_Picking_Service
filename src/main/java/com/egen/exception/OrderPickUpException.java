package com.egen.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPickUpException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public OrderPickUpException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderPickUpException(String message) {
        super(message);
    }
}
