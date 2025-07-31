package com.example.teambhp.service.impl;

import com.example.teambhp.dto.CategoryDto;
import com.example.teambhp.dto.ThreadDto;
import com.example.teambhp.model.Category;
import com.example.teambhp.model.Threads;
import com.example.teambhp.model.User;
import com.example.teambhp.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ThreadDto createThread(ThreadDto dto) {
        User author = userRepo.findByUsername(dto.getCreatedByUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the actual Category entity (not just a DTO)
        CategoryDto catDto = dto.getCategory();
        Category category = categoryRepository.findByName(catDto.getName())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Threads thread = new Threads();
        thread.setTitle(dto.getTitle());
        thread.setCreatedBy(author);
        thread.setCategory(category); // ✅ Managed entity with proper ID

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
