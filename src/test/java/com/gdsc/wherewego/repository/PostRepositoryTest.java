package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.Post;
import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.gdsc.wherewego.domain.category.District.*;
import static com.gdsc.wherewego.domain.category.FoodType.*;
import static com.gdsc.wherewego.domain.category.Theme.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게시물 JPA 연결 테스트")
class PostRepositoryTest extends RepositoryTest{
    private User user;
    private Schedule schedule;
    private Post post;

    @BeforeEach
    void init() {
        user = User.builder()
                .nickname("창윤")
                .password("thisispassword")
                .profileUrl("www.asd.com")
                .build();

        schedule = Schedule.builder()
                .user(user)
                .name("나의 일정")
                .startDate(LocalDate.of(2023,7,16))
                .endDate(LocalDate.of(2023,7,16))
                .foodType(KOREAN)
                .theme(HISTORY)
                .district(BUK_GU)
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
