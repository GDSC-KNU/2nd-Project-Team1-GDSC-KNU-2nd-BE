package com.gdsc.wherewego.service;

import com.gdsc.wherewego.api.dto.UserDistrictRequestDTO;
import com.gdsc.wherewego.api.dto.UserFoodRequestDTO;
import com.gdsc.wherewego.domain.Category;
import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.domain.category.District;
import com.gdsc.wherewego.domain.category.FoodType;
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

        category.setDistrict(districtList);
        schedule.setBasicInfo(userDistrictRequestDTO.getWithPeople(), userDistrictRequestDTO.getBudget());
    }

    @Transactional
    public void selectFood(UserFoodRequestDTO userFoodRequestDTO, Long scheduleId){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = optionalSchedule.get();

        Category category = categoryRepository.findCategoryByScheduleId(schedule.getId());
        List<String> foodList = userFoodRequestDTO.getFood();
        List<FoodType> foodTypeList = new ArrayList<>();

        for (String food : foodList) {
            FoodType foodType = new FoodType(category, food);
            foodTypeList.add(foodType);
        }

        category.setFoodType(foodTypeList);
        schedule.setTransportation(userFoodRequestDTO.getTransportation());
    }
}
