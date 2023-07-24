package com.gdsc.wherewego.service;

import com.gdsc.wherewego.api.dto.UserDistrictRequestDTO;
import com.gdsc.wherewego.domain.Category;
import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.domain.category.District;
import com.gdsc.wherewego.repository.CategoryRepository;
import com.gdsc.wherewego.repository.ScheduleRepository;
import com.gdsc.wherewego.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final ScheduleRepository scheduleRepository;

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void selectDistrict(UserDistrictRequestDTO userDistrictRequestDTO, Long scheduleId){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = optionalSchedule.get();

        Category category = categoryRepository.findCategoryByScheduleId(schedule.getId());

        List<String> cityList = userDistrictRequestDTO.getCity();
        List<District> districtList = new ArrayList<>();

        for (String city : cityList) {
            District district = new District(category, city);
            districtList.add(district);
        }

        category.addDistrict(districtList);
        schedule.setBasicInfo(userDistrictRequestDTO.getWithPeople(), userDistrictRequestDTO.getBudget());
    }
}
