package com.practice.controller.admin;

import com.practice.category.model.*;
import com.practice.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto create(@RequestBody CategoryCreateDto categoryCreate) {
        Category category = categoryMapper.fromCreate(categoryCreate);
        return categoryMapper.toResponse(categoryService.create(category));
    }

    @PatchMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDto update(@PathVariable int catId,
                                      @RequestBody CategoryUpdateDto categoryUpdate) {
        Category category = categoryMapper.fromUpdate(categoryUpdate);
        return categoryMapper.toResponse(categoryService.update(catId, category));
    }

//    TODO: дописать
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{catId}")
    public void delete(@PathVariable int catId) {
        categoryService.delete(catId);
    }
}
