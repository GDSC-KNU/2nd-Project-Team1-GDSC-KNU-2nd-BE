package com.gdsc.wherewego.api.controller;

import com.gdsc.wherewego.api.dto.*;
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


    @PostMapping("/date")
    public ResponseEntity<String> makeSchedule(@RequestBody CategoryDTO.UserDayRequestDTO userDayRequestDTO, @PathVariable("userId") Long id){
        scheduleService.saveSchedule(userDayRequestDTO, id);
        return ResponseEntity.ok().body("스케쥴 생성 성공");
    }

    @PostMapping("/district")
    public ResponseEntity<String> selectDistrict(@RequestBody CategoryDTO.UserDistrictRequestDTO userDistrictRequestDTO, @PathVariable("userID") Long userId, @PathVariable("scheduleId") Long scheduleId, BindingResult bindingResult){
        categoryService.selectDistrict(userDistrictRequestDTO, scheduleId);
        return ResponseEntity.ok().body("도시 선택 완료");
    }

    @PostMapping("/food")
    public ResponseEntity<String> selectFood(@RequestBody CategoryDTO.UserFoodRequestDTO userFoodRequestDTO, @PathVariable("userID") Long userId, @PathVariable("scheduleId") Long scheduleId){
        categoryService.selectFood(userFoodRequestDTO, scheduleId);
        return ResponseEntity.ok().body("음식 종류 선택 완료");
    }

    @PostMapping("/theme")
    public ResponseEntity<String> selectTheme(@RequestBody CategoryDTO.UserThemeRequestDTO userThemeRequestDTO, @PathVariable("userID") Long userId, @PathVariable("scheduleId") Long scheduleId){
        categoryService.selectTheme(userThemeRequestDTO, scheduleId);
        return ResponseEntity.ok().body("테마 종류 선택 완료");
    }
}
