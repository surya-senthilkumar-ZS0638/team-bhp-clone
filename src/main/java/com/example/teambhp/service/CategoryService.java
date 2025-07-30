package com.example.teambhp.service;

import com.example.teambhp.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto dto);
    List<CategoryDto> getAllCategories();
    Optional<CategoryDto> findByName(String name);
}
