package com.gdsc.wherewego.service;

import com.gdsc.wherewego.domain.Category;
import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.dto.request.CategoryRequest;
import com.gdsc.wherewego.oauth.SecurityUtil;
import com.gdsc.wherewego.repository.ScheduleRepository;
import com.gdsc.wherewego.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final ScheduleRepository scheduleRepository;

    private final UserRepository userRepository;
    private final ScheduleService scheduleService;


    @Transactional
    public void makeCategory(CategoryRequest categoryRequestDTO){
        String currentUserEmail = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(currentUserEmail).orElseThrow(IllegalArgumentException::new);

        Category category = categoryRequestDTO.toEntity();
        scheduleService.saveSchedule(user,category);

    }
}
