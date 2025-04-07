package com.practice.user.service;

import com.practice.common.exception.EntityAlreadyExistsException;
import com.practice.common.exception.NotFoundException;
import com.practice.user.model.User;
import com.practice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            String email = user.getEmail();
            throw new EntityAlreadyExistsException("Пользователь с почтой " + email + " уже существует");
        }
        return userRepository.save(user);
    }

    public List<User> findAll(List<Integer> ids, int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);

        if (ids == null || ids.isEmpty()) {
            return userRepository.findAll(pageable).getContent();
        } else {
            return userRepository.findAllByIdIn(ids, pageable);
        }
    }

    public void delete(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не найден"));

        userRepository.delete(user);
    }
}
