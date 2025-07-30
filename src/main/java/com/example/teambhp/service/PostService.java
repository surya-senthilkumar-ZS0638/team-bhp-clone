package com.example.teambhp.service;

import com.example.teambhp.dto.PostDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface PostService {
    @Transactional
    PostDto createPost(PostDto dto);

    List<PostDto> getPostsByThread(Long threadId);
}
