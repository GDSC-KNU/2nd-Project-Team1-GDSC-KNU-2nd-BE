package com.gdsc.wherewego.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Exception extends RuntimeException {
    private final HttpStatus status;

    public Exception(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
    }
}
