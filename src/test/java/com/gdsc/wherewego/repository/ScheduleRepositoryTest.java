package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.*;
import com.gdsc.wherewego.domain.category.District;
import com.gdsc.wherewego.domain.category.FoodType;
import com.gdsc.wherewego.domain.category.Theme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("스케줄 JPA 연결 테스트")
@Disabled
public class ScheduleRepositoryTest extends RepositoryTest{
    private User user;
    private Schedule schedule;

    private Theme theme;
    private FoodType foodType;
    private District district;
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
    }

    @Test
    @DisplayName("일정을 저장한다.")
    void save() {
        //given, when
        users.save(user);
        Schedule savedSchedule = schedules.save(schedule);

        //then
        assertThat(savedSchedule.getId()).isNotNull();
        assertThat(savedSchedule).isEqualTo(schedule);
    }
}