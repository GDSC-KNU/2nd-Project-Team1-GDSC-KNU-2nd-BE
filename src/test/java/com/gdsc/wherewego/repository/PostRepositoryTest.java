package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.*;
import com.gdsc.wherewego.domain.enumCategory.Transportation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게시물 JPA 연결 테스트")
@Disabled
class PostRepositoryTest extends RepositoryTest{
    private User user;
    private Schedule schedule;
    private Post post;
    private Category category;
    @BeforeEach
    void init() {
        List<String> district = new ArrayList<String>(Arrays.asList("북구","동구","중구"));
        List<String> foodType = new ArrayList<String>(Arrays.asList("한식","일식"));
        List<String> theme = new ArrayList<String>(Arrays.asList("카페","경치관람","수목원/정원","가벼운 산책","역사","미술/예술","자연/과학","테마박물관"));

        user = User.builder()
                .nickname("창윤")
                .email("aaa@gmail.com")
                .profileUrl("www.asd.com")
                .build();

        category = Category.builder()
                .startDate("2023/07/16")
                .endDate("2023/07/16")
                .district(district)
                .foodType(foodType)
                .theme(theme)
                .budget(1000000)
                .withPeople(2)
                .transportation(Transportation.BUS)
                .build();

        schedule = Schedule.builder()
                .user(user)
                .name("나의 일정")
                .category(category)
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
