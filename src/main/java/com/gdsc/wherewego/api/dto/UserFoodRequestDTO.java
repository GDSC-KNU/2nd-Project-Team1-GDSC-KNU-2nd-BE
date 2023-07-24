package com.gdsc.wherewego.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserFoodRequestDTO {
    private List<String> food;
    private String transportation;

}
