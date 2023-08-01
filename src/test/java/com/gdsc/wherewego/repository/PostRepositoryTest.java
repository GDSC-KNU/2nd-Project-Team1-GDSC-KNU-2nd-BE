package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.Image;
import com.gdsc.wherewego.domain.Post;
import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.domain.category.District;
import com.gdsc.wherewego.domain.category.FoodType;
import com.gdsc.wherewego.domain.category.Theme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게시물 JPA 연결 테스트")
@Disabled
class PostRepositoryTest extends RepositoryTest{
    private User user;
    private Schedule schedule;
    private Post post;
    private District district;
    private FoodType foodType;
    private Theme theme;
    @BeforeEach
    void init() {
        user = User.builder()
                .nickname("창윤")
                .email("aaa@gmail.com")
                .profileUrl("www.asd.com")
                .build();

        schedule = Schedule.builder()
                .user(user)
                .name("나의 일정")
                .startDate("2023/07/16")
                .endDate("2023/07/16")
                .build();

        foodType = FoodType.builder()
                .schedule(schedule)
                .type("한식")
                .build();

        theme = Theme.builder()
                .schedule(schedule)
                .type("사격")
                .build();

        district = District.builder()
                .schedule(schedule)
                .city("북구")
                .build();

        post = Post.builder()
                .schedule(schedule)
                .title("나의 일기")
                .content("재밌었다.")
                .build();
    }

    @Test
    @DisplayName("게시물을 저장한다.")
    void save() {
        //given, when
        Post savedPost = posts.save(post);

        //then
        assertThat(savedPost.getId()).isNotNull();
        assertThat(savedPost).isEqualTo(post);
    }
}
