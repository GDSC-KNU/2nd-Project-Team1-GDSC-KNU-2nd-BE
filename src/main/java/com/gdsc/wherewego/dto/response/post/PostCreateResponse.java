package com.gdsc.wherewego.dto.response.post;

import com.gdsc.wherewego.domain.Post;

public record PostCreateResponse(
        Long id
) {
    public static PostCreateResponse from(final Post post) {
        return new PostCreateResponse(post.getId());
    }
}
