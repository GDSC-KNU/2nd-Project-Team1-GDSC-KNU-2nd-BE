package com.gdsc.wherewego.service;

import com.gdsc.wherewego.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final ScheduleRepository scheduleRepository;




}
