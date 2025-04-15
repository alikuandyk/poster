package com.practice.request.service;

import com.practice.request.model.ApplicationStatus;
import com.practice.request.model.Request;
import com.practice.request.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public List<Request>, List<Request> updateParticipationRequestStatusByRequesterId(int userId, int eventId, List<Integer> requestIds, ApplicationStatus status) {

    }
}
