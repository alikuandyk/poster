package com.practice.user.model;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User fromCreate(UserCreateDto userCreate) {
        User user = new User();
        user.setName(userCreate.getName());
        user.setEmail(userCreate.getEmail());
        return user;
    }

    public UserResponseDto toResponse(User user) {
        UserResponseDto userResponse = new UserResponseDto();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }
}
