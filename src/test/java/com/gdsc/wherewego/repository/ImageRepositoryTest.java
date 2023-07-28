package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static com.gdsc.wherewego.domain.constant.District.*;
import static com.gdsc.wherewego.domain.constant.FoodType.*;
import static com.gdsc.wherewego.domain.constant.Theme.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사진 JPA 연결 테스트")
@Disabled
class ImageRepositoryTest extends RepositoryTest {
    private User user;
    private Schedule schedule;
    private Post post;
    private Image image;

    @BeforeEach
    void init() {
        user = User.builder()
                .nickname("창윤")
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
