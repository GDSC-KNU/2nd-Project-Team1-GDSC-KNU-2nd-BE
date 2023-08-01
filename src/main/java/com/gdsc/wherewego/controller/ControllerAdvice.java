package com.gdsc.wherewego.controller;

import com.gdsc.wherewego.dto.response.ErrorResponse;
import com.gdsc.wherewego.exception.Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(final Exception exception) {
        log(exception);
        return ResponseEntity.status(exception.getStatus())
                .body(ErrorResponse.from(exception));
    }

    private void log(Exception exception) {
        log.info(exception.getMessage(), exception);
    }
}
