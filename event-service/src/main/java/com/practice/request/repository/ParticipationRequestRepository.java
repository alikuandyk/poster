package com.practice.request.repository;

import com.practice.request.model.ParticipationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Integer> {
}
