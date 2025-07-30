package com.example.teambhp.service;

import com.example.teambhp.dto.ThreadDto;

import java.util.List;

public interface ThreadService {
    ThreadDto createThread(ThreadDto dto);
    List<ThreadDto> getAllThreads();
    ThreadDto getThreadById(Long id);
}
