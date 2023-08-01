package com.gdsc.wherewego.dto.response;

import com.gdsc.wherewego.domain.Schedule;

public record ScheduleFindResponse(
        String scheduleName,
        String startDate,
        String endDate,
        String district,
        String foodType,
        String theme,
        boolean isDone
) {
    public static ScheduleFindResponse of(final Schedule schedule) {
        return new ScheduleFindResponse(
                schedule.getName(),
                schedule.getStartDate().toString(),
                schedule.getEndDate().toString(),
                schedule.getDistrict().toString(),
                schedule.getFoodType().toString(),
                schedule.getTheme().toString(),
                schedule.isDone()
        );
    }
}
