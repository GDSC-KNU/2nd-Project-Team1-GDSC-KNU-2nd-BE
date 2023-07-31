package com.gdsc.wherewego.exception;

import org.springframework.http.HttpStatus;

public class NoAuthorizationException extends Exception{
    private static final String MESSAGE = "%s 권한이 없습니다.";

    public NoAuthorizationException(String usage) {
        super(String.format(MESSAGE, usage), HttpStatus.FORBIDDEN);
    }
}
