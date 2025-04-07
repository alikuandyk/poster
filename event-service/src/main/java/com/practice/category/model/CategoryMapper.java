package com.practice.category.model;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category fromCreate(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setName(categoryCreateDto.getName());
        return category;
    }

    public Category fromUpdate(CategoryUpdateDto categoryUpdateDto) {
        Category category = new Category();
        category.setName(categoryUpdateDto.getName());
        return category;
    }

    public CategoryResponseDto toResponse(Category category) {
        CategoryResponseDto categoryResponse = new CategoryResponseDto();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
}
