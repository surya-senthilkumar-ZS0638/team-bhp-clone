package com.example.teambhp.repository;

import com.example.teambhp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByThreadId(Long threadId);

}