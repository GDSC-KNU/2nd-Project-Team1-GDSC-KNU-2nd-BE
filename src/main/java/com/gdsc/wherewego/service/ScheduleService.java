package com.gdsc.wherewego.service;

import com.gdsc.wherewego.domain.Category;
import com.gdsc.wherewego.dto.request.CategoryRequest;
import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.oauth.SecurityUtil;
import com.gdsc.wherewego.repository.ScheduleRepository;
import com.gdsc.wherewego.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long saveSchedule(User user, Category category){
        Schedule schedule = new Schedule(user, category);
        scheduleRepository.save(schedule);
        return schedule.getId();
    }

    public Schedule findByUserId(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();

        Schedule schedule = scheduleRepository.findByUserId(user.getId());
        return schedule;
    }
}
