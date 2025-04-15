package com.practice.event.repository;

import com.practice.event.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByInitiatorId(int userId, Pageable pageable);
    Optional<Event> findByIdAndInitiatorId(int userId, int eventId);
}
