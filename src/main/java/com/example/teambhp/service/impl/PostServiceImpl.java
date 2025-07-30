package com.example.teambhp.service.impl;

import com.example.teambhp.dto.PostDto;
import com.example.teambhp.model.Post;
import com.example.teambhp.model.Threads;
import com.example.teambhp.model.User;
import com.example.teambhp.repository.PostRepository;
import com.example.teambhp.repository.ThreadRepository;
import com.example.teambhp.repository.UserRepository;
import com.example.teambhp.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepo;
    private final ThreadRepository threadRepo;
    private final UserRepository userRepo;

    @Transactional
    @Override
    public PostDto createPost(PostDto dto) {
        Threads thread = threadRepo.findById(dto.getThreadId())
                .orElseThrow(() -> new RuntimeException("Thread not found"));
        User author = userRepo.findByUsername(dto.getAuthorUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setThread(thread);
        post.setAuthor(author);
        post.setContent(dto.getContent());
        Post saved = postRepo.save(post);

        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<PostDto> getPostsByThread(Long threadId) {
        return postRepo.findByThreadId(threadId).stream().map(p -> {
            PostDto dto = new PostDto();
            dto.setId(p.getId());
            dto.setThreadId(threadId);
            dto.setAuthorUsername(p.getAuthor().getUsername());
            dto.setContent(p.getContent());
            // optionally postedAt
            return dto;
        }).toList();
    }
}
