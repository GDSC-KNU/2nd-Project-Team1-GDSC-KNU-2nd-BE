package com.gdsc.wherewego.service;

import com.gdsc.wherewego.domain.Image;
import com.gdsc.wherewego.domain.Post;
import com.gdsc.wherewego.dto.response.PostCreateResponse;
import com.gdsc.wherewego.dto.response.PostFindAllResponse;
import com.gdsc.wherewego.dto.response.PostFindResponse;
import com.gdsc.wherewego.exception.NoSuchElementException;
import com.gdsc.wherewego.repository.ImageRepository;
import com.gdsc.wherewego.repository.PostRepository;
import com.gdsc.wherewego.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import java.util.stream.Collectors;

import static com.gdsc.wherewego.domain.constant.Constants.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository posts;
    private final ScheduleRepository schedules;
    private final ImageRepository images;

    @Transactional(readOnly = true)
    public PostFindResponse findPost(final Long postId) {
        //TODO: Redis에 유저명, 게시물아이디로 좋아요 이력 조회
        boolean liked = false;

        return PostFindResponse.of(posts.findById(postId)
                .orElseThrow(() -> new NoSuchElementException(POST)), liked);
    }

    @Transactional(readOnly = true)
    public PostFindAllResponse findPosts() {
        //TODO: 로그인 된 유저 정보 가져오기
        //TODO: Redis에 유저명, 게시물아이디로 좋아요 이력 조회
        return PostFindAllResponse.of(posts.findAllByCreatedBy("yun").stream()
                .map(post -> PostFindResponse.of(post, true))
                .collect(Collectors.toList()));
    }

    public PostCreateResponse savePost(String title, String content, Long scheduleId, List<MultipartFile> multipartFiles) {
        Post savePost = posts.save(Post.builder()
                .title(title)
                .content(content)
                .schedule(schedules.findById(scheduleId)
                        .orElseThrow(() -> new NoSuchElementException(SCHEDULE)))
                .build());

        multipartFiles.stream()
                .map(this::upload)
                .map(url -> Image.builder().post(savePost).url(url).build())
                .forEach(images::save);

        return PostCreateResponse.from(savePost);
    }

    public void updatePost(final Long postId, String title, String content, List<MultipartFile> multipartFiles) {
        Post post = posts.findById(postId)
                .orElseThrow(() -> new NoSuchElementException(POST));

        post.setTitle(title);
        post.setContent(content);

        multipartFiles.stream()
                .map(this::upload)
                .map(url -> Image.builder().post(post).url(url).build())
                .forEach(images::save);
    }

    public void deletePost(final Long postId) {
        Post deletePost = posts.findById(postId)
                .orElseThrow(() -> new NoSuchElementException(POST));

        posts.delete(deletePost);
    }

    public void likePost(final Long postId) {
        //TODO: Redis 연결 후 좋아요 기능 구현
    }

    public void unlikePost(final Long postId) {
        //TODO: Redis 연결 후 좋아요 기능 구현
    }

    public String upload(MultipartFile image) {
        //TODO: amazon S3 업로드 후 저장된 url 가져오기
        return "";
    }
}
