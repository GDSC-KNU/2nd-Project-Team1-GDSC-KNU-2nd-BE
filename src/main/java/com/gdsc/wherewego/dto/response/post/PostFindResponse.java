package com.gdsc.wherewego.dto.response;

import com.gdsc.wherewego.domain.Post;

public record PostFindResponse(
        String scheduleName,
        String startDate,
        String endDate,
        String district,
        boolean liked,
        int likes,
        String title,
        String content,
        String createdAt,
        UserFindResponse user
) {
    public static PostFindResponse of(final Post post, boolean liked) {
        return new PostFindResponse(
                post.getSchedule().getName(),
                post.getSchedule().getStartDate().toString(),
                post.getSchedule().getEndDate().toString(),
                post.getSchedule().getDistrict().getDescription(),
                liked,
                post.getLikes(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt().toString(),
                UserFindResponse.of(post.getSchedule().getUser())
        );
    }
}
