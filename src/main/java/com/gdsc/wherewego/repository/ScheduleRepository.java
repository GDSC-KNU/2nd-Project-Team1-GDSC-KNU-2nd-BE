package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByUser(User user);
}
