package com.practice.category.service;

import com.practice.common.exception.ConflictException;
import com.practice.common.exception.NotFoundException;
import com.practice.category.model.Category;
import com.practice.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
/*    TODO: Создать исключении и выбросить их правильно.
       Создать events для того чтобы доделать метод delete().
       Написать контроллер
 */
//    Вобщем, не понял как обработать исключения. Не уверен что сделал логично
//    Еще не знаю правильно ли написал delete

    private final CategoryRepository categoryRepository;

    public Category create(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            String categoryName = category.getName();
            throw new ConflictException("Категория с именем " + categoryName + " уже существует");
        }
        return categoryRepository.save(category);
    }

    public Category update(int catId, Category updatedCategory) {
        if (categoryRepository.findByName(updatedCategory.getName()).isPresent()) {
            String updCatName = updatedCategory.getName();
            throw new ConflictException("Категория с именем " + updCatName + " уже существует");
        }

        Optional<Category> optCat = categoryRepository.findById(catId);
        if (optCat.isEmpty()) {
            throw new NotFoundException("Категория с id " + catId + " не найден");
        }

        Category existingCategory = optCat.get();
        existingCategory.setName(updatedCategory.getName());
        return categoryRepository.save(existingCategory);
    }

//    TODO: не должен удаляться если в категории есть события
    public void delete(int catId) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Категория с id " + catId + " не найден"));

        categoryRepository.delete(category);
    }

//    TODO: учти
    public List<Category> findAll(int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        return categoryRepository.findAll(pageable).getContent();
    }

    public Category findById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Категория с id " + id + " не найден"));
    }
}
