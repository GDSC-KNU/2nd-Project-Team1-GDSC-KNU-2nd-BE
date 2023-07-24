package com.gdsc.wherewego.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDayRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
}
