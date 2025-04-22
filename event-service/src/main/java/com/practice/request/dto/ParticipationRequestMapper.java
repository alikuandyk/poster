package com.practice.request.dto;

import com.practice.request.model.ParticipationRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParticipationRequestMapper {
    public ParticipationRequestResponseDto toResponse(ParticipationRequest request) {
        ParticipationRequestResponseDto participationRequestResponse = new ParticipationRequestResponseDto();

        participationRequestResponse.setId(request.getId());
        participationRequestResponse.setRequester(request.getRequester().getId());
        participationRequestResponse.setEvent(request.getEvent().getId());
        participationRequestResponse.setCreated(request.getCreated());
        participationRequestResponse.setStatus(request.getStatus());

        return participationRequestResponse;
    }

    public List<ParticipationRequestResponseDto> toListResponse(List<ParticipationRequest> requests) {
        List<ParticipationRequestResponseDto> requestsResponse = new ArrayList<>();

        for (ParticipationRequest request : requests) {
            requestsResponse.add(toResponse(request));
        }

        return requestsResponse;
    }
}
