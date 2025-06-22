package com.practice.request.repository;

import com.practice.request.model.ParticipationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Integer> {
    List<ParticipationRequest> findAllRequestsByEventId(int eventId);

    Optional<ParticipationRequest> findByRequesterIdAndEventId(int requesterId, int eventId);
}
