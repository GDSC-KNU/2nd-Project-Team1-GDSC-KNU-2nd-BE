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
import java.util.NoSuchElementException;


import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사진 JPA 연결 테스트")
@Disabled
class ImageRepositoryTest extends RepositoryTest {
    private User user;
    private Category category;
    private Schedule schedule;
    private Post post;
    private Image image;

    @BeforeEach
    void init() {
        List<String> district = new ArrayList<String>(Arrays.asList("북구","동구","중구"));
        List<String> foodType = new ArrayList<String>(Arrays.asList("한식","일식"));
        List<String> theme = new ArrayList<String>(Arrays.asList("카페","경치관람","수목원/정원","가벼운 산책","역사","미술/예술","자연/과학","테마박물관"));

        user = User.builder()
                .nickname("창윤")
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

        image = Image.builder()
                .post(post)
                .url("www.image.com")
                .build();
    }

    @Test
    @DisplayName("이미지를 저장한다.")
    void save() {
        //given, when
        users.save(user);
        schedules.save(schedule);
        posts.save(post);
        Image savedImage = images.save(image);

        //then
        assertThat(savedImage.getId()).isNotNull();
        assertThat(savedImage).isEqualTo(image);
    }

    @Test
    @DisplayName("id로 이미지를 찾아올 수 있다.")
    void findById() {
        //given
        users.save(user);
        schedules.save(schedule);
        posts.save(post);
        Image savedImage = images.save(image);

        //when
        Image findImage = images.findById(savedImage.getId())
                .orElseThrow(NoSuchElementException::new);

        //then
        assertThat(findImage).isEqualTo(savedImage);
    }

    @Test
    @DisplayName("게시물의 모든 이미지를 조회할 수 있다.")
    void findAllByMember() {
        //given
        users.save(user);
        schedules.save(schedule);
        posts.save(post);
        Image image2 = Image.builder()
                .post(post)
                .url("www.url.com")
                .build();
        Image savedImage1 = images.save(image);
        Image savedImage2 = images.save(image2);

        //when
        List<Image> findImages = images.findAllByPost(post);

        //then
        assertThat(findImages).containsExactlyInAnyOrderElementsOf(List.of(savedImage1, savedImage2));
    }
}