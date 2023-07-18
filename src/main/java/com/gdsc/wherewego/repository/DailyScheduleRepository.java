package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.DailySchedule;
import com.gdsc.wherewego.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {
    List<DailySchedule> findAllBySchedule(final Schedule schedule);
}
