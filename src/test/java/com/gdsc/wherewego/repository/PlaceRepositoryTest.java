package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static com.gdsc.wherewego.domain.constant.District.*;
import static com.gdsc.wherewego.domain.constant.Theme.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("장소 JPA 연결 테스트")
class PlaceRepositoryTest extends RepositoryTest {
    private User user;
    private Schedule schedule;
    private DailyPlace dailyPlace;
    private DailySchedule dailySchedule;
    private Place place;

    @BeforeEach
    void init() {
        place = Place.builder()
                .name("경북대학교")
                .district(BUK_GU)
                .theme(WALK)
                .latitude(35.8905477)
                .longitude(128.6121117)
                .build();
    }

    @Test
    @DisplayName("장소를 저장한다.")
    void save() {
        //given, when
        Place savedPlace = places.save(place);

        //then
        assertThat(savedPlace.getId()).isNotNull();
        assertThat(savedPlace).isEqualTo(place);
    }

    @Test
    @DisplayName("id로 장소를 찾아올 수 있다.")
    void findById() {
        //given
        Place savedPlace = places.save(place);

        //when
        Place findPlace = places.findById(savedPlace.getId())
                .orElseThrow(NoSuchElementException::new);

        //then
        assertThat(findPlace).isEqualTo(savedPlace);
    }

    @Test
    @DisplayName("위도, 경도 정보로 장소를 찾을 수 있다.")
    void findByLatAndLng() {
        //given
        Place savedPlace = places.save(place);

        //when
        Place findPlace = places.findByLatitudeAndLongitude(savedPlace.getLatitude(), savedPlace.getLongitude())
                .orElseThrow(NoSuchElementException::new);

        //then
        assertThat(findPlace).isEqualTo(savedPlace);
    }
}
