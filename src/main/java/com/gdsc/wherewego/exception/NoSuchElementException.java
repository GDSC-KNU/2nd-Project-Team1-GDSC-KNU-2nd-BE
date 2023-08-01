package com.gdsc.wherewego.exception;

import org.springframework.http.HttpStatus;

public class NoSuchElementException extends Exception {
    private static final String MESSAGE = "존재하지 않는 %s입니다.";

    public NoSuchElementException(String usage) {
        super(String.format(MESSAGE, usage), HttpStatus.NOT_FOUND);
    }
}
