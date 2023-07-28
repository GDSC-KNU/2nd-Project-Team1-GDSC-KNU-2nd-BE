package com.gdsc.wherewego.controller;

import com.gdsc.wherewego.config.RestDocsTestSupport;
import com.gdsc.wherewego.domain.Image;
import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.domain.constant.District;
import com.gdsc.wherewego.domain.constant.FoodType;
import com.gdsc.wherewego.domain.constant.Theme;
import com.gdsc.wherewego.dto.request.PostCreateRequest;
import com.gdsc.wherewego.dto.request.PostUpdateRequest;
import com.gdsc.wherewego.dto.response.ScheduleFindResponse;
import com.gdsc.wherewego.dto.response.post.PostCreateResponse;
import com.gdsc.wherewego.dto.response.post.PostFindAllResponse;
import com.gdsc.wherewego.dto.response.post.PostFindResponse;
import com.gdsc.wherewego.dto.response.UserFindResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import org.springframework.restdocs.payload.JsonFieldType;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest extends RestDocsTestSupport {

    private final PostFindResponse findResponse = new PostFindResponse(
            ScheduleFindResponse.of(Schedule.builder()
                    .name("경북대 투어")
                    .startDate(LocalDate.of(2023,7,27))
                    .endDate(LocalDate.of(2023,7,27))
                    .district(District.BUK_GU)
                    .foodType(FoodType.KOREAN)
                    .theme(Theme.HISTORY).build()),
            false,
            0,
            "내 일기",
            "재밌었다.",
            "2023-07-27 21:46:28.822215",
            UserFindResponse.of(User.builder()
                    .nickname("wwg")
                    .profileUrl("www.profile.com").build()));

    @DisplayName("게시물 정보를 id로 가져온다.")
    @Test
    void findPostById() throws Exception {
        //given
        Long postId = 1L;
        given(postService.findPost(any()))
                .willReturn(findResponse);

        //when, then
        mvc.perform(get("/api/posts/{postId}", postId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("postId").description("조회할 게시물 id")
                        ),
                        responseFields(
                                fieldWithPath("schedule").description("일정 정보"),
                                fieldWithPath("schedule.scheduleName").description("일정 이름"),
                                fieldWithPath("schedule.startDate").description("일정 시작 날짜"),
                                fieldWithPath("schedule.endDate").description("일정 종료 날짜"),
                                fieldWithPath("schedule.district").description("일정에 포함된 지역"),
                                fieldWithPath("schedule.foodType").description("일정에 포함된 음식 종류"),
                                fieldWithPath("schedule.theme").description("일정의 테마"),
                                fieldWithPath("schedule.isDone").type(JsonFieldType.BOOLEAN).description("일정 완료 여부"),
                                fieldWithPath("title").description("게시물 제목"),
                                fieldWithPath("content").description("게시물 내용"),
                                fieldWithPath("liked").type(JsonFieldType.BOOLEAN).description("현재 사용자 좋아요 클릭 여부"),
                                fieldWithPath("likes").description("좋아요 수"),
                                fieldWithPath("createdAt").description("작성 일시"),
                                fieldWithPath("user").description("작성자 정보"),
                                fieldWithPath("user.nickname").description("작성자 이름"),
                                fieldWithPath("user.profileUrl").description("작성자 프로필 사진 url")
                        )
                ));
    }

    @DisplayName("게시물 정보를 모두 가져온다.")
    @Test
    void findPosts() throws Exception {
        Long postId = 1L;
        //given
        given(postService.findPosts())
                .willReturn(PostFindAllResponse.of(List.of(findResponse)));

        //when, then
        mvc.perform(get("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        responseFields(
                                List.of(
                                        fieldWithPath("posts").type(JsonFieldType.ARRAY).description("나의 일정 목록"),
                                        fieldWithPath("posts[].schedule").description("일정 정보"),
                                        fieldWithPath("posts[].schedule.scheduleName").description("일정 이름"),
                                        fieldWithPath("posts[].schedule.startDate").description("일정 시작 날짜"),
                                        fieldWithPath("posts[].schedule.endDate").description("일정 종료 날짜"),
                                        fieldWithPath("posts[].schedule.district").description("일정에 포함된 지역"),
                                        fieldWithPath("posts[].schedule.foodType").description("일정에 포함된 음식 종류"),
                                        fieldWithPath("posts[].schedule.theme").description("일정의 테마"),
                                        fieldWithPath("posts[].schedule.isDone").type(JsonFieldType.BOOLEAN).description("일정 완료 여부"),
                                        fieldWithPath("posts[].title").description("게시물 제목"),
                                        fieldWithPath("posts[].content").description("게시물 내용"),
                                        fieldWithPath("posts[].liked").type(JsonFieldType.BOOLEAN).description("현재 사용자 좋아요 클릭 여부"),
                                        fieldWithPath("posts[].likes").description("좋아요 수"),
                                        fieldWithPath("posts[].createdAt").description("작성 일시"),
                                        fieldWithPath("posts[].user").description("작성자 정보"),
                                        fieldWithPath("posts[].user.nickname").description("작성자 이름"),
                                        fieldWithPath("posts[].user.profileUrl").description("작성자 프로필 사진 url")
                                )
                        )
                ));
    }

    @DisplayName("게시물 정보를 저장한다.")
    @Test
    void savePost() throws Exception {
        //given
        PostCreateRequest createRequest = new PostCreateRequest(
                1L,
                "나의 일기",
                "재밌었다."
        );

        Image image = Image.builder().url("test.com").build();

//        final String path = "src/test/resources/testImage.jpg";
//        FileInputStream fileInputStream = new FileInputStream(path);
        MockMultipartFile testImage = new MockMultipartFile(
                "images",
                "testImage.jpg",
                "multipart/form-data",
                createJson(image).getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile request = new MockMultipartFile(
                "createRequest",
                null,
                "application/json",
                createJson(createRequest).getBytes(StandardCharsets.UTF_8)
        );

        given(postService.savePost(any(), any(), any(), any()))
                .willReturn(new PostCreateResponse(1L));

        //when, then
        mvc.perform(multipart("/api/posts")
                        .file(testImage)
                        .file(request)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated())
                .andDo(restDocs.document(
                        requestParts(
                                partWithName("images").description("첨부한 이미지 파일 목록"),
                                partWithName("createRequest").description("게시물 생성에 필요한 정보 (스케줄 id : 게시물 생성에 필요한 완료된 일정의 id)")
                        )
                ));
    }

    @DisplayName("게시물 정보를 수정한다.")
    @Test
    void updatePost() throws Exception {
        //given
        Long postId = 1L;

        PostUpdateRequest updateRequest = new PostUpdateRequest(
                "수정한 일기",
                "그닥 재미있지 않았다."
        );

        Image image = Image.builder().url("test.com").build();

//        final String path = "src/test/resources/testImage.jpg";
//        FileInputStream fileInputStream = new FileInputStream(path);
        MockMultipartFile testImage = new MockMultipartFile(
                "images",
                "testImage.jpg",
                "multipart/form-data",
//                fileInputStream
                createJson(image).getBytes(StandardCharsets.UTF_8)
        );
        MockMultipartFile request = new MockMultipartFile(
                "updateRequest",
                null,
                "application/json",
                createJson(updateRequest).getBytes(StandardCharsets.UTF_8)
        );

        doNothing().when(postService).updatePost(any(), any(), any(), any());

        mvc.perform(multipart(HttpMethod.PUT, "/api/posts/{postId}", postId)
                .file(testImage)
                .file(request)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        requestParts(
                                partWithName("images").description("바뀐 첨부 이미지 파일 목록"),
                                partWithName("updateRequest").description("수정할 게시물 정보 (제목, 내용)")
                        )
                ));
}

    @DisplayName("게시물을 삭제한다.")
    @Test
    void deletePost() throws Exception {
        //given
        Long postId = 1L;

        //when, then
        mvc.perform(delete("/api/posts/{postId}", postId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("postId").description("삭제할 게시물의 id")
                        )
                ));
    }

    @DisplayName("게시물에 좋아요를 누른다.")
    @Test
    void likePost() throws Exception {
        //given
        Long postId = 1L;

        //when, then
        mvc.perform(post("/api/posts/{postId}/like", postId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("postId").description("좋아요를 반영할 게시물의 id")
                        )
                ));
    }

    @DisplayName("게시물 좋아요를 취소한다.")
    @Test
    void unlikePost() throws Exception {
        //given
        Long postId = 1L;

        //when, then
        mvc.perform(delete("/api/posts/{postId}/like", postId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("postId").description("좋아요를 취소할 게시물의 id")
                        )
                ));
    }
}
