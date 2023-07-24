package com.gdsc.wherewego.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDistrictRequestDTO {
    private List<String> city;

    private Integer withPeople;

    private Integer budget;
}
