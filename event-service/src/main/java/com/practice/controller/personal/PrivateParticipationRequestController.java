package com.practice.controller.personal;

import com.practice.request.dto.ParticipationRequestMapper;
import com.practice.request.dto.ParticipationRequestResponseDto;
import com.practice.request.dto.ParticipationRequestUpdateResponseDto;
import com.practice.request.dto.ParticipationRequestUpdateDto;
import com.practice.request.model.ParticipationRequest;
import com.practice.request.service.ParticipationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}")
public class PrivateParticipationRequestController {
    private final ParticipationRequestService requestService;
    private final ParticipationRequestMapper requestMapper;

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/events/{eventId}/requests")
    public ParticipationRequestUpdateResponseDto updateStatus(@PathVariable int userId, @PathVariable int eventId,
                                                              @RequestBody ParticipationRequestUpdateDto requestUpdate) {
        List<ParticipationRequest> confirmedRequests = new ArrayList<>();
        List<ParticipationRequest> rejectedRequests = new ArrayList<>();
        requestService.updateStatus(userId, eventId, requestUpdate.getRequestIds(), requestUpdate.getStatus(), confirmedRequests, rejectedRequests);

        List<ParticipationRequestResponseDto> confirmed = requestMapper.toListResponse(confirmedRequests);
        List<ParticipationRequestResponseDto> rejected = requestMapper.toListResponse(rejectedRequests);

        ParticipationRequestUpdateResponseDto requestUpdateResponse = new ParticipationRequestUpdateResponseDto();
        requestUpdateResponse.setConfirmedRequests(confirmed);
        requestUpdateResponse.setRejectedRequests(rejected);

        return requestUpdateResponse;
    }
}
