package com.gdsc.wherewego.domain.enumCategory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Transportation {
    BUS("버스"),
    RENT("렌트카");

    @Getter
    private final String description;
}
