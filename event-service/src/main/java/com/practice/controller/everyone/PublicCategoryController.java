package com.practice.controller.everyone;

import com.practice.category.model.Category;
import com.practice.category.model.CategoryMapper;
import com.practice.category.model.CategoryResponseDto;
import com.practice.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class PublicCategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponseDto> findAll(@RequestParam(defaultValue = "0") int from,
                                             @RequestParam(defaultValue = "10") int size) {

        List<Category> categories = categoryService.findAll(from, size);

        return categories.stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @GetMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDto findById(@PathVariable int catId) {
        return categoryMapper.toResponse(categoryService.findById(catId));
    }
}
