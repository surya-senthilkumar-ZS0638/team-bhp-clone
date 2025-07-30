package com.example.teambhp.service.impl;

import com.example.teambhp.dto.CategoryDto;
import com.example.teambhp.dto.ThreadDto;
import com.example.teambhp.model.Category;
import com.example.teambhp.model.Threads;
import com.example.teambhp.model.User;
import com.example.teambhp.repository.ThreadRepository;
import com.example.teambhp.repository.UserRepository;
import com.example.teambhp.service.CategoryService;
import com.example.teambhp.service.ThreadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThreadServiceImpl implements ThreadService {
    private final ThreadRepository threadRepo;
    private final UserRepository userRepo;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public ThreadDto createThread(ThreadDto dto) {
        User author = userRepo.findByUsername(dto.getCreatedByUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        CategoryDto catDto = dto.getCategory();
        CategoryDto fetchedCat = categoryService.findByName(catDto.getName())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Threads thread = new Threads();
        thread.setCategory(dto.getTitle());
        thread.setCreatedBy(author);
        thread.setCategory(new Category(fetchedCat.getId())); // or fetch entity
        Threads saved = threadRepo.save(thread);

        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<ThreadDto> getAllThreads() {
        return threadRepo.findAll().stream().map(t -> {
            ThreadDto dto = new ThreadDto();
            dto.setId(t.getId());
            dto.setTitle(t.getTitle());
            dto.setCreatedByUsername(t.getCreatedBy().getUsername());
            dto.setCategory(new CategoryDto(t.getCategory().getId(), t.getCategory().getName(), t.getCategory().getDescription()));
            return dto;
        }).toList();
    }

    @Override
    public ThreadDto getThreadById(Long id) {
        Threads t = threadRepo.findById(id).orElseThrow();
        ThreadDto dto = new ThreadDto();
        dto.setId(t.getId());
        dto.setTitle(t.getTitle());
        dto.setCreatedByUsername(t.getCreatedBy().getUsername());
        dto.setCategory(new CategoryDto(t.getCategory().getId(), t.getCategory().getName(), t.getCategory().getDescription()));

        return dto;
    }
}
