package com.gdsc.wherewego.controller;

import com.gdsc.wherewego.dto.request.CategoryRequest;
import com.gdsc.wherewego.service.CategoryService;
import com.gdsc.wherewego.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ScheduleService scheduleService;

    @PostMapping("/choice")
    public ResponseEntity<String> makeSchedule1(@RequestBody CategoryRequest categoryRequest){
        categoryService.makeCategory(categoryRequest);
        return ResponseEntity.ok().body("스케쥴 생성");
    }

}
