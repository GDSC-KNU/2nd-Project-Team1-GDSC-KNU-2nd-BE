package com.gdsc.wherewego.dto.response.post;

import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.category.District;

import java.util.stream.Collectors;

public record PostScheduleResponse(
        String startDate,
        String endDate,
        String district
) {
    public static PostScheduleResponse of(final Schedule schedule) {
        return new PostScheduleResponse(
                schedule.getStartDate(),
                schedule.getEndDate(),
                schedule.getDistrict().stream()
                        .map(District::getCity)
                        .collect(Collectors.joining(","))
        );
    }
}
