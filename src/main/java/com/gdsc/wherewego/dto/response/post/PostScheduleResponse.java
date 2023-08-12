package com.gdsc.wherewego.dto.response.post;

import com.gdsc.wherewego.domain.Category;
import com.gdsc.wherewego.domain.Schedule;

import java.util.stream.Collectors;

public record PostScheduleResponse(
        String startDate,
        String endDate,
        String district
) {
    public static PostScheduleResponse of(final Schedule schedule) {
        Category category = schedule.getCategory();
        return new PostScheduleResponse(
                category.getStartDate(),
                category.getEndDate(),
                category.getDistrict().stream().collect(Collectors.joining(","))
        );
    }
}
