package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.DailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {
}
