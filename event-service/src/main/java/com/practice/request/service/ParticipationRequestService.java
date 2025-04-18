package com.practice.request.service;

import com.practice.common.exception.ConflictException;
import com.practice.common.exception.NotFoundException;
import com.practice.event.model.Event;
import com.practice.event.repository.EventRepository;
import com.practice.request.dto.ParticipationRequestMapper;
import com.practice.request.dto.ParticipationRequestResponseDto;
import com.practice.request.dto.ParticipationRequestStatusUpdateResponseDto;
import com.practice.request.dto.ParticipationRequestUpdateDto;
import com.practice.request.model.ParticipationRequest;
import com.practice.request.model.RequestStatus;
import com.practice.request.repository.ParticipationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationRequestService {
    private final ParticipationRequestRepository requestRepository;
    private final ParticipationRequestMapper requestMapper;
    private final EventRepository eventRepository;

    public ParticipationRequestStatusUpdateResponseDto updateParticipationRequestStatusByRequesterId(int userId,
                                                                                                     int eventId,
                                                                                                     ParticipationRequestUpdateDto participationRequestUpdate) {
        Event event = findEventById(eventId);
        RequestStatus updateStatus = participationRequestUpdate.getStatus();

        ParticipationRequestStatusUpdateResponseDto participationRequestStatusUpdateResponse = new ParticipationRequestStatusUpdateResponseDto();
        List<ParticipationRequestResponseDto> confirmedRequests = new ArrayList<>();
        List<ParticipationRequestResponseDto> rejectedRequests = new ArrayList<>();

        List<ParticipationRequest> requests = participationRequestUpdate.getRequestIds().stream()
                .map(requestId -> requestRepository.findById(requestId)
                        .orElseThrow(() -> new NotFoundException("Заявка с id " + requestId + " не найден")))
                .toList();

        if (event.getParticipantLimit().equals(event.getConfirmedRequests())) {
            throw new ConflictException("Достигнут лимит одобренных заявок");
        }

        requests.stream()
                .filter(request -> !request.getStatus().equals("PENDING"))
                .findFirst()
                .ifPresent(request -> {
                    throw new ConflictException("Статус можно изменить только у заявок, находящихся в состоянии ожидания. Статус заявки с id "
                            + request.getId() + ": " + request.getStatus());
                });

        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            confirmedRequests.addAll(requests.stream()
                    .map(requestMapper::toResponse)
                    .toList());

            participationRequestStatusUpdateResponse.setConfirmedRequests(confirmedRequests);
            return participationRequestStatusUpdateResponse;
        }

        for (ParticipationRequest request : requests) {
            if (event.getParticipantLimit().equals(event.getConfirmedRequests())) {
                for (ParticipationRequest rejectedRequest : requests) {
                    rejectedRequest.setStatus(RequestStatus.REJECTED);
                    requestRepository.save(rejectedRequest);
                    rejectedRequests.add(requestMapper.toResponse(rejectedRequest));
                }
                participationRequestStatusUpdateResponse.setRejectedRequests(rejectedRequests);
                return participationRequestStatusUpdateResponse;
            }
            request.setStatus(updateStatus);
            requestRepository.save(request);
            confirmedRequests.add(requestMapper.toResponse(request));
            participationRequestStatusUpdateResponse.setConfirmedRequests(confirmedRequests);
            requests.remove(request);

            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
            eventRepository.save(event);
        }
        return participationRequestStatusUpdateResponse;
    }

    private Event findEventById(int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("События с id " + eventId + " не найден"));
    }
}
