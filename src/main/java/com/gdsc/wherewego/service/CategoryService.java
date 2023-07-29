package com.gdsc.wherewego.service;

import com.gdsc.wherewego.api.dto.CategoryDTO;
import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.category.District;
import com.gdsc.wherewego.domain.category.FoodType;
import com.gdsc.wherewego.domain.category.Theme;
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

    @Transactional
    public void selectDistrict(CategoryDTO.UserDistrictRequestDTO userDistrictRequestDTO, Long scheduleId){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = optionalSchedule.get();

        List<String> cityList = userDistrictRequestDTO.getCity();
        List<District> districtList = new ArrayList<>();

        for (String city : cityList) {
            District district = new District(schedule, city);
            districtList.add(district);
        }

        schedule.setDistrict(districtList);
        schedule.setBasicInfo(userDistrictRequestDTO.getWithPeople(), userDistrictRequestDTO.getBudget());
    }

    @Transactional
    public void selectFood(CategoryDTO.UserFoodRequestDTO userFoodRequestDTO, Long scheduleId){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = optionalSchedule.get();

        List<String> foodList = userFoodRequestDTO.getFood();
        List<FoodType> foodTypeList = new ArrayList<>();

        for (String food : foodList) {
            FoodType foodType = new FoodType(schedule, food);
            foodTypeList.add(foodType);
        }

        schedule.setFoodType(foodTypeList);
        schedule.setTransportation(userFoodRequestDTO.getTransportation());
    }

    @Transactional
    public void selectTheme(CategoryDTO.UserThemeRequestDTO userThemeRequestDTO, Long scheduleId){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = optionalSchedule.get();

        List<String> stringThemeList = userThemeRequestDTO.getTheme();
        List<Theme> themeList = new ArrayList<>();

        for (String t : stringThemeList) {
            Theme theme = new Theme(schedule, t);
            themeList.add(theme);
        }
        schedule.setTheme(themeList);
    }
}
