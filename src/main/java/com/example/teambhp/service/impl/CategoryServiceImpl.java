package com.example.teambhp.service.impl;

import com.example.teambhp.dto.CategoryDto;
import com.example.teambhp.model.Category;
import com.example.teambhp.repository.CategoryRepository;
import com.example.teambhp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        Category cat = new Category();
        cat.setName(dto.getName());
        cat.setDescription(dto.getDescription());
        Category saved = categoryRepository.save(cat);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(c -> {
            CategoryDto dto = new CategoryDto();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setDescription(c.getDescription());
            return dto;
        }).toList();
    }

    @Override
    public Optional<CategoryDto> findByName(String name) {
        return categoryRepository.findByName(name).map(c -> {
            CategoryDto dto = new CategoryDto();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setDescription(c.getDescription());
            return dto;
        });
    }
}
