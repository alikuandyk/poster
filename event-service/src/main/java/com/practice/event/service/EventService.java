package com.practice.event.service;

import com.practice.category.model.Category;
import com.practice.category.repository.CategoryRepository;
import com.practice.common.exception.NotFoundException;
import com.practice.event.model.Event;
import com.practice.event.model.EventState;
import com.practice.event.repository.EventRepository;
import com.practice.user.model.User;
import com.practice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Event create(int userId, Event event) {
        User initiator = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не существует"));

        Integer categoryId = event.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Категория с id " + categoryId + " не существует"));

        event.setState(EventState.PENDING);
        event.setInitiator(initiator);
        event.setCreatedOn(LocalDateTime.now());
        event.setConfirmedRequests(0);
        event.setViews(0);

        return eventRepository.save(event);
    }

    public Event findAllByInitiatorId(int userId, int from, int size) {
        return null;
    }
}
