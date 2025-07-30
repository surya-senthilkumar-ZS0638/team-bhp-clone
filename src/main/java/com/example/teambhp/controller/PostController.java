package com.example.teambhp.controller;

import com.example.teambhp.dto.PostDto;
import com.example.teambhp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getPostsByThread(@RequestParam("threadId") Long threadId) {
        List<PostDto> list = postService.getPostsByThread(threadId);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto) {
        PostDto created = postService.createPost(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
