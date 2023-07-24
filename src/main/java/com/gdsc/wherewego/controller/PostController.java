package com.gdsc.wherewego.controller;

import com.gdsc.wherewego.dto.response.PostCreateResponse;
import com.gdsc.wherewego.dto.response.PostFindAllResponse;
import com.gdsc.wherewego.dto.response.PostFindResponse;
import com.gdsc.wherewego.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("{postId}")
    public ResponseEntity<PostFindResponse> find(@PathVariable final Long postId) {
        return ResponseEntity.ok(postService.findPost(postId));
    }

    @GetMapping
    public ResponseEntity<PostFindAllResponse> findAll() {
        return ResponseEntity.ok(postService.findPosts());
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestParam(required = false, value = "images") List<MultipartFile> images,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "scheduleId") Long scheduleId) {
        PostCreateResponse postCreateResponse = postService.savePost(title, content, scheduleId, images);
        return ResponseEntity.created(URI.create("api/posts/" + postCreateResponse.id()))
                .build();
    }

    @PutMapping("{postId}")
    public ResponseEntity<Void> update(
            @PathVariable final Long postId,
            @RequestParam(required = false, value = "images") List<MultipartFile> images,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content) {
        postService.updatePost(postId, title, content, images);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Void> delete(@PathVariable final Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{postId}/like")
    public ResponseEntity<Void> like(@PathVariable final Long postId) {
        postService.likePost(postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{postId}/like")
    public ResponseEntity<Void> unlike(@PathVariable final Long postId) {
        postService.unlikePost(postId);
        return ResponseEntity.ok().build();
    }
}
