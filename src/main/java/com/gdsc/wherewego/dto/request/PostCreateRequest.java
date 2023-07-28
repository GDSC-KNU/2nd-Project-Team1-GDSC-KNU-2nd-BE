package com.gdsc.wherewego.dto.request;

public record PostCreateRequest(
        Long scheduleId,
        String title,
        String content
) {
}
