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


import static com.gdsc.wherewego.domain.enumCategory.DistrictEnum.BUK_GU;
import static com.gdsc.wherewego.domain.enumCategory.ThemeEnum.WALK;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("하루 일정 JPA 연결 테스트")
@Disabled
class DailyScheduleRepositoryTest extends RepositoryTest{
    private User user;
    private Schedule schedule;
    private Category category;
    private DailyPlace dailyPlace;
    private DailySchedule dailySchedule;
    private Place place;
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

        dailySchedule = DailySchedule.builder()
                .schedule(schedule)
                .date(LocalDate.of(2023,7,16))
                .build();

        place = Place.builder()
                .name("경북대학교")
                .district(BUK_GU)
                .theme(WALK)
                .latitude(35.8905477)
                .longitude(128.6121117)
                .build();

        dailyPlace = DailyPlace.builder()
                .place(place)
                .dailySchedule(dailySchedule)
                .build();
    }

    @Test
    @DisplayName("하루 일정을 저장한다.")
    void save() {
        //given, when
        User savedUser = users.save(user);
        Place savedPlace = places.save(place);
        Schedule savedSchedule = schedules.save(schedule);
        DailySchedule savedDaily = dailySchedules.save(dailySchedule);
        DailyPlace savedDailyPlace = dailyPlaces.save(dailyPlace);

        //then
        assertThat(savedDaily.getId()).isNotNull();
        assertThat(savedDaily).isEqualTo(dailySchedule);
    }

    @Test
    @DisplayName("일정에 포함된 하루 일정을 찾아올 수 있다.")
    void findBySchedule() {
        //given
        User savedUser = users.save(user);
        Place savedPlace = places.save(place);
        Schedule savedSchedule = schedules.save(schedule);
        DailySchedule savedDaily = dailySchedules.save(dailySchedule);
        DailyPlace savedDailyPlace = dailyPlaces.save(dailyPlace);

        //when
        List<DailySchedule> findDaily = dailySchedules.findAllBySchedule(schedule);

        //then
        assertThat(findDaily).containsExactlyInAnyOrderElementsOf(List.of(savedDaily));
    }
}