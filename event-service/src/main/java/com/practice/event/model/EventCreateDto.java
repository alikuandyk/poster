package com.practice.event.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventCreateDto {
    @NotBlank
    @Size(min = 3, max = 120, message = "Заголовок события долен содержать от 3 до 120 символов")
    String title;

    @NotBlank
    @Size(min = 20, max = 2000, message = "Краткое описание события долен содержать от 20 до 2000 символов")
    String annotation;

    @NotBlank
    @Size(min = 20, max = 7000, message = "Полное описание события долен содержать от 20 до 7000 символов")
    String description;

    @NotNull
    Integer categoryId;

    @NotNull
    @Future(message = "Дата событии не может быть раньше, чем через два часа от текущего момента")
    LocalDateTime eventDate;

    @NotNull
    @Valid
    Location id;

    @PositiveOrZero
    Integer participantLimit;

    @NotNull
    Boolean paid;

    Boolean requestModeration;
}
