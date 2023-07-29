package com.gdsc.wherewego.domain.enumCategory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DistrictEnum {
    JUNG_GU("중구"),
    SEO_GU("서구"),
    DONG_GU("동구"),
    NAM_GU("남구"),
    BUK_GU("북구"),
    SUSEONG_GU("수성구"),
    DALSEO_GU("달서구"),
    DALSEONG_GUN("달성군"),
    GUNWI_GUN("군위군");

    @Getter
    private final String description;
}
