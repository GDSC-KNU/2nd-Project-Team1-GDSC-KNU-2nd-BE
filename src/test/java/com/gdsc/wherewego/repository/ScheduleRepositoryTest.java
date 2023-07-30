//package com.gdsc.wherewego.repository;
//
//import com.gdsc.wherewego.api.dto.CategoryDTO;
//import com.gdsc.wherewego.domain.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DisplayName("스케줄 JPA 연결 테스트")
//public class ScheduleRepositoryTest extends RepositoryTest{
//    private User user;
//    private Schedule schedule;
//
//    @BeforeEach
//    void init() {
//        user = User.builder()
//                .nickname("창윤")
//                .password("thisispassword")
//                .profileUrl("www.asd.com")
//                .build();
//
//        schedule = Schedule.builder()
//                .user(user)
//                .name("나의 일정")
//                .startDate(LocalDate.of(2023, 7, 16))
//                .endDate(LocalDate.of(2023, 7, 16))
//                .foodType("KOREAN")
//                .theme("HISTORY")
//                .district("BUK_GU")
//                .withPeople(2)
//                .budget(1000)
//                .transportation("버스")
//                .build();
//    }
//
//    @Test
//    @DisplayName("일정을 저장한다.")
//    void save() {
//        //given, when
//        users.save(user);
//        Schedule savedSchedule = schedules.save(schedule);
//
//        //then
//        assertThat(savedSchedule.getId()).isNotNull();
//        assertThat(savedSchedule).isEqualTo(schedule);
//    }
//
//
//}
