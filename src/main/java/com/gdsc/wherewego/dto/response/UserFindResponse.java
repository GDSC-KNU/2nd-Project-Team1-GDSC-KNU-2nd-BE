package com.gdsc.wherewego.dto.response;

import com.gdsc.wherewego.domain.User;

public record UserFindResponse(
        String nickname,
        String profileUrl
) {
    public static UserFindResponse of(final User user) {
        return new UserFindResponse(
            user.getNickname(),
            user.getProfileUrl()
        );
    }
}
