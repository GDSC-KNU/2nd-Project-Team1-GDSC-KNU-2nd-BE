package com.gdsc.wherewego.api.controller;

import com.gdsc.wherewego.api.dto.UserDayRequestDTO;
import com.gdsc.wherewego.api.dto.UserDistrictRequestDTO;
import com.gdsc.wherewego.service.CategoryService;
import com.gdsc.wherewego.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;
    private final ScheduleService scheduleService;

    /*
    일정 날짜 입력받기
     */

    @PostMapping("date/{userId}")
    public ResponseEntity<String> makeSchedule(@RequestBody UserDayRequestDTO userDayRequestDTO, @PathVariable("userId") Long id){
        scheduleService.saveSchedule(userDayRequestDTO, id);
        return ResponseEntity.ok().body("스케쥴 생성 성공");
    }

    @PostMapping("/city/{userId}/{scheduleId}")
    public ResponseEntity<String> selectDistrict(@RequestBody UserDistrictRequestDTO userDistrictRequestDTO, @PathVariable("userID") Long userId, @PathVariable("scheduleId") Long scheduleId){
        categoryService.selectDistrict(userDistrictRequestDTO, scheduleId);
        return ResponseEntity.ok().body("도시 선택 완료");
    }

}
