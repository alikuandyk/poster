package com.practice.compilation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationUpdateDto {
    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(min = 1, max = 50, message = "Заголовок должен содержать от 1 до 50 символов")
    String title;

    Boolean pinned = false;

    @UniqueElements(message = "Список событий должен содержать только уникальные значения")
    List<Integer> events;
}
