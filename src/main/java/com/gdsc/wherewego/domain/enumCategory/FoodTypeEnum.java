package com.gdsc.wherewego.domain.enumCategory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FoodTypeEnum {
    KOREAN("한식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    WESTERN("양식");

    @Getter
    private final String description;
}
