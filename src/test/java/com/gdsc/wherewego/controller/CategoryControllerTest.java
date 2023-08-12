package com.gdsc.wherewego.controller;

import com.gdsc.wherewego.domain.enumCategory.Transportation;
import com.gdsc.wherewego.dto.request.CategoryRequest;
import com.gdsc.wherewego.config.RestDocsTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CategoryController.class)
public class CategoryControllerTest extends RestDocsTestSupport {

    @Test
    @DisplayName("스케쥴을 생성합니다")
    public void makeSchedule() throws Exception {
        List<String> district = new ArrayList<String>(Arrays.asList("북구","동구","중구"));
        List<String> foodType = new ArrayList<String>(Arrays.asList("한식","일식"));
        List<String> theme = new ArrayList<String>(Arrays.asList("카페","경치관람","수목원/정원","가벼운 산책","역사","미술/예술","자연/과학","테마박물관"));

        CategoryRequest categoryRequest = CategoryRequest.builder()
                .startDate("2023/08/07")
                .endDate("2023/08/10")
                .district(district)
                .foodType(foodType)
                .theme(theme)
                .withPeople(2)
                .budget(100000)
                .transportation(Transportation.BUS)
                .build();

        mvc.perform(
                post("/api/category/choice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest))
        )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("startDate").description("일정 시작 날짜"),
                                        fieldWithPath("endDate").description("일정 종료 날짜"),
                                        fieldWithPath("district").description("어디로 여행가시나요?"),
                                        fieldWithPath("withPeople").description("몇명이서 여행을 가시나요?"),
                                        fieldWithPath("budget").description("예산이 얼마인가요?"),
                                        fieldWithPath("transportation").description("어떤 교통편을 이용하시나요?"),
                                        fieldWithPath("foodType").description("어떤 음식을 좋아하세요?"),
                                        fieldWithPath("theme").description("무엇을 하고 싶으신가요?")
                                )
                        )

                );
    }



}
