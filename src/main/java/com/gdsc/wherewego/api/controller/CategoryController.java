package com.gdsc.wherewego.api.controller;

import com.gdsc.wherewego.api.dto.UserDayRequestDTO;
import com.gdsc.wherewego.api.dto.UserDistrictRequestDTO;
import com.gdsc.wherewego.api.dto.UserFoodRequestDTO;
import com.gdsc.wherewego.api.dto.UserThemeRequestDTO;
import com.gdsc.wherewego.service.CategoryService;
import com.gdsc.wherewego.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;
    private final ScheduleService scheduleService;


    @PostMapping("date/{userId}")
    public ResponseEntity<String> makeSchedule(@RequestBody UserDayRequestDTO userDayRequestDTO, @PathVariable("userId") Long id){
        scheduleService.saveSchedule(userDayRequestDTO, id);
        return ResponseEntity.ok().body("스케쥴 생성 성공");
    }

    @PostMapping("/city/{userId}/{scheduleId}")
    public ResponseEntity<String> selectDistrict(@RequestBody UserDistrictRequestDTO userDistrictRequestDTO, @PathVariable("userID") Long userId, @PathVariable("scheduleId") Long scheduleId, BindingResult bindingResult){
        categoryService.selectDistrict(userDistrictRequestDTO, scheduleId);
        return ResponseEntity.ok().body("도시 선택 완료");
    }

    @PostMapping("/select/{userId}/{scheduleId}")
    public ResponseEntity<String> selectFood(@RequestBody UserFoodRequestDTO userFoodRequestDTO, @PathVariable("userID") Long userId, @PathVariable("scheduleId") Long scheduleId){
        categoryService.selectFood(userFoodRequestDTO, scheduleId);
        return ResponseEntity.ok().body("음식 종류 선택 완료");
    }

    @PostMapping("/theme/{userId}/{scheduleId}")
    public ResponseEntity<String> selectTheme(@RequestBody UserThemeRequestDTO userThemeRequestDTO, @PathVariable("userID") Long userId, @PathVariable("scheduleId") Long scheduleId){
        categoryService.selectTheme(userThemeRequestDTO, scheduleId);
        return ResponseEntity.ok().body("테마 종류 선택 완료");
    }
}
