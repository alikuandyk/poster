package com.practice.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateDto {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 2, max = 250, message = "Имя должен содержать только от 2 до 250 символов")
    String name;

    @NotBlank(message = "Почта не может быть пустым")
    @Size(min = 6, max = 254, message = "Имя должен содержать только от 6 до 254 символов")
    @Email(message = "Почта пользователя должна быть в формате \"user@mail.com\"")
    String email;
}
