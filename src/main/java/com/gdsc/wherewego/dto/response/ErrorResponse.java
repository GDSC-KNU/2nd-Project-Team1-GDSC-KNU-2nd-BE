package com.gdsc.wherewego.dto.response;

public record ErrorResponse(
        String message
) {
    public static ErrorResponse from(RuntimeException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
