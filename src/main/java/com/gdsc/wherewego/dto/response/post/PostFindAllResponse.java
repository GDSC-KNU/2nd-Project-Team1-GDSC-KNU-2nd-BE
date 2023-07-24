package com.gdsc.wherewego.dto.response;

import java.util.List;

public record PostFindAllResponse(
        List<PostFindResponse> posts
) {
    public static PostFindAllResponse of(final List<PostFindResponse> postFindResponses) {
        return new PostFindAllResponse(postFindResponses);
    }
}
