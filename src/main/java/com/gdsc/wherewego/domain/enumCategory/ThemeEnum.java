package com.gdsc.wherewego.domain.enumCategory;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ThemeEnum {
    CAFE("카페"),
    SCENERY("경치관람"),
    ARBORETUM("수목원/정원"),
    THEME("테마파크/아쿠아리움"),
    WALK("가벼운 산책"),
    HIKING("가벼운 등산"),
    HISTORY("역사"),
    ART("미술/예술"),
    NATURE("자연/과학"),
    SHOOTING("사격"),
    ANIMAL("동물/이색체험"),
    GUIDE("가이드 투어/클래스");

    @Getter
    private final String description;
}
