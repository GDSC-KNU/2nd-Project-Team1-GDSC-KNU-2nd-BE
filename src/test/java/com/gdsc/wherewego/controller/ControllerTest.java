package com.gdsc.wherewego.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.wherewego.service.CategoryService;
import com.gdsc.wherewego.service.PostService;
import com.gdsc.wherewego.service.ScheduleService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({
        PostController.class,
        CategoryController.class,
})
public abstract class ControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired protected MockMvc mvc;

    @MockBean
    protected PostService postService;

    @MockBean
    protected CategoryService categoryService;

    @MockBean
    protected ScheduleService scheduleService;

    protected String createJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }
}
