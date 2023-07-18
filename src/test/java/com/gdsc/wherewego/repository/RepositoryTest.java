package com.gdsc.wherewego.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RepositoryTest {
    @Autowired protected UserRepository users;
    @Autowired protected ScheduleRepository schedules;
    @Autowired protected PostRepository posts;
    @Autowired protected ImageRepository images;
    @Autowired protected DailyScheduleRepository dailySchedules;
    @Autowired protected DailyPlaceRepository dailyPlaces;
    @Autowired protected PlaceRepository places;
}
