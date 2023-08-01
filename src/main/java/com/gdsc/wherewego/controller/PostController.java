package com.gdsc.wherewego.controller;

import com.gdsc.wherewego.dto.request.PostCreateRequest;
import com.gdsc.wherewego.dto.request.PostUpdateRequest;
import com.gdsc.wherewego.dto.response.post.PostCreateResponse;
import com.gdsc.wherewego.dto.response.post.PostFindAllResponse;
import com.gdsc.wherewego.dto.response.post.PostFindResponse;
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

    @GetMapping("/{postId}")
    public ResponseEntity<PostFindResponse> find(@PathVariable final Long postId) {
        return ResponseEntity.ok(postService.findPost(postId));
    }

    @GetMapping
    public ResponseEntity<PostFindAllResponse> findAll() {
        return ResponseEntity.ok(postService.findPosts());
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestPart(required = false, value = "images") List<MultipartFile> images,
            @RequestPart(value = "createRequest") PostCreateRequest createRequest) {
        PostCreateResponse postCreateResponse = postService.savePost(createRequest.title(), createRequest.content(), createRequest.scheduleId(), images);
        return ResponseEntity.created(URI.create("api/posts/" + postCreateResponse.id()))
                .build();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> update(
            @PathVariable final Long postId,
            @RequestPart(required = false, value = "images") List<MultipartFile> images,
            @RequestPart(value = "updateRequest") PostUpdateRequest postUpdateRequest) {
        postService.updatePost(postId, images, postUpdateRequest.title(), postUpdateRequest.content());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable final Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> like(@PathVariable final Long postId) {
        postService.likePost(postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}/like")
    public ResponseEntity<Void> unlike(@PathVariable final Long postId) {
        postService.unlikePost(postId);
        return ResponseEntity.noContent().build();
    }
}
