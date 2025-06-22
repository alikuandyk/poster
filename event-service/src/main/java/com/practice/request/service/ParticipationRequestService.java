package com.practice.request.service;

import com.practice.common.exception.ConflictException;
import com.practice.common.exception.NotFoundException;
import com.practice.event.model.Event;
import com.practice.event.model.EventState;
import com.practice.event.repository.EventRepository;
import com.practice.request.model.ParticipationRequest;
import com.practice.request.model.RequestStatus;
import com.practice.request.repository.ParticipationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipationRequestService {
    private final ParticipationRequestRepository requestRepository;
    private final EventRepository eventRepository;

    public ParticipationRequest create(int userId, int eventId) {

    }

    public void updateStatus(int userId, int eventId,
                             List<Integer> requestIds, RequestStatus updateStatus,
                             List<ParticipationRequest> confirmedRequests, List<ParticipationRequest> rejectedRequests) {
//        Сперва проверяю, доступен ли ивент. Потом выбрасываю исключение если достигнут лимит одобренных заявок
        Event event = findEventById(eventId);
        if (event.getParticipantLimit().equals(event.getConfirmedRequests())) {
            throw new ConflictException("Достигнут лимит одобренных заявок");
        }

//        Дальше проверяю, если к событию не надо подтверждение запроса или если лимит равно нулю,
//        то подтверждаю все запросы, добавляю их всех в список, сохраняю в базе данных.
//        Так же обновляю количество подтвержденных запросов в событии
        List<ParticipationRequest> requests = requestRepository.findAllById(requestIds);
        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            for (ParticipationRequest request : requests) {
                request.setStatus(updateStatus);
                confirmedRequests.add(request);
                requestRepository.save(request);
            }
            event.setConfirmedRequests(event.getConfirmedRequests() + requests.size());
            eventRepository.save(event);
            return;
        }

//        Здесь прохожусь по всем запросам. Сперва проверяю статус запроса, если оно не в ожидании то сбрасываю исключение
//        Если исключение не было, дальше прежде чем обновлять статус запроса, проверяю, не исчерпан ли лимит одобренных заявок.
//        Если не исчерпан, то обновляю статус и обновляю в базе данных. Так же обновляю количество подтвержденных запросов в событии
        for (ParticipationRequest request : requests) {
            if (!request.getStatus().equals(RequestStatus.PENDING)) {
                throw new ConflictException("Статус можно изменить только у заявок, находящихся в состоянии ожидания. Статус заявки с id "
                        + request.getId() + ": " + request.getStatus());
            }

            if (event.getConfirmedRequests().equals(event.getParticipantLimit())) {
                request.setStatus(RequestStatus.REJECTED);
                rejectedRequests.add(request);
            } else {
                request.setStatus(updateStatus);
                confirmedRequests.add(request);
                event.setConfirmedRequests(event.getConfirmedRequests() + 1);
            }
            requestRepository.save(request);
        }
        eventRepository.save(event);
    }

    public List<ParticipationRequest> findAllRequestsByEventId(int userId, int eventId) {
        Event event = findEventById(eventId);

        if (event.getInitiator().getId() != userId) {
            throw new ConflictException("Пользователь с id " + userId + " не является создателем событий с id " + event.getId());
        }

        return requestRepository.findAllRequestsByEventId(eventId);
    }

    private Event findEventById(int eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("События с id " + eventId + " не найден"));

        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new NotFoundException("Событие с id " + eventId + " не доступно. Статус: " + event.getState());
        }

        return event;
    }
}
