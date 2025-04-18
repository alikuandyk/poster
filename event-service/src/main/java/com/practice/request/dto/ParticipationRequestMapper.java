package com.practice.request.dto;

import com.practice.request.model.ParticipationRequest;
import org.springframework.stereotype.Component;

@Component
public class ParticipationRequestMapper {
    public ParticipationRequestResponseDto toResponse(ParticipationRequest participationRequest) {
        ParticipationRequestResponseDto participationRequestResponse = new ParticipationRequestResponseDto();

        participationRequestResponse.setId(participationRequest.getId());
        participationRequestResponse.setRequester(participationRequest.getRequester().getId());
        participationRequestResponse.setEvent(participationRequest.getEvent().getId());
        participationRequestResponse.setCreated(participationRequest.getCreated());
        participationRequestResponse.setStatus(participationRequest.getStatus());

        return participationRequestResponse;
    }
}
