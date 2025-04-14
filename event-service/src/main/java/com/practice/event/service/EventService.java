package com.practice.event.service;

import com.practice.category.repository.CategoryRepository;
import com.practice.common.exception.ConflictException;
import com.practice.common.exception.NotFoundException;
import com.practice.event.model.Event;
import com.practice.event.model.EventState;
import com.practice.event.repository.EventRepository;
import com.practice.user.model.User;
import com.practice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Event create(int userId, Event event) {
        User initiator = findUserById(userId);

        Integer categoryId = event.getCategory().getId();
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Категория с id " + categoryId + " не существует"));

        event.setState(EventState.PENDING);
        event.setInitiator(initiator);
        event.setCreatedOn(LocalDateTime.now());
        event.setConfirmedRequests(0);
        event.setViews(0);

        return eventRepository.save(event);
    }

    public List<Event> findAllByInitiatorId(int userId, int from, int size) {
        findUserById(userId);

        Pageable pageable = PageRequest.of(from / size, size);
        return eventRepository.findAllByInitiatorId(userId, pageable);
    }

    public Event findEventByInitiatorId(int userId, int eventId) {
        findUserById(userId);

        return eventRepository.findByIdAndInitiatorId(userId, eventId)
                .orElseThrow(() -> new NotFoundException(
                        "Событие с id " + eventId + " не найдено у пользователя с id " + userId));
    }

    public Event updateEventByInitiatorId(int userId, int eventId, Event eventUpdate) {
        Event event = findEventByInitiatorId(userId, eventId);

        if (event.getState().equals(EventState.PUBLISHED)) {
            throw new ConflictException("Изменить можно только отмененные события или события в состоянии ожидания");
        }

        if (eventUpdate.getEventDate() != null && (eventUpdate.getEventDate().isBefore(LocalDateTime.now().plusHours(2)))) {
            throw new ConflictException("Дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента");
        }

        if (eventUpdate.getState() != null) {
            event.setState(eventUpdate.getState());
        }

        if (eventUpdate.getTitle() != null) {
            event.setTitle(eventUpdate.getTitle());
        }

        if (eventUpdate.getAnnotation() != null) {
            event.setAnnotation(eventUpdate.getAnnotation());
        }

        if (eventUpdate.getDescription() != null) {
            event.setDescription(eventUpdate.getDescription());
        }

        if (eventUpdate.getCategory() != null) {
            event.setCategory(eventUpdate.getCategory());
        }

        if (eventUpdate.getEventDate() != null) {
            event.setEventDate(eventUpdate.getEventDate());
        }

        if (eventUpdate.getParticipantLimit() != null) {
            event.setParticipantLimit(eventUpdate.getParticipantLimit());
        }

        if (eventUpdate.getLocation() != null) {
            event.setLocation(eventUpdate.getLocation());
        }

        if (eventUpdate.getPaid() != null) {
            event.setPaid(eventUpdate.getPaid());
        }

        if (eventUpdate.getRequestModeration() != null) {
            event.setRequestModeration(eventUpdate.getPaid());
        }

        return event;
    }

    public Event updateStatusParticipationInEvent(int userId, int eventId, Event event) {

    }

    private User findUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + userId + " не существует"));
    }
}
