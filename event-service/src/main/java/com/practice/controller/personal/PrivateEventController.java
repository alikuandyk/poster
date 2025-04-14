package com.practice.controller.personal;

import com.practice.event.dto.*;
import com.practice.event.model.Event;
import com.practice.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class PrivateEventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/events")
    public EventFullResponseDto create(@PathVariable int userId,
                                       @RequestBody EventCreateDto eventCreate) {

        Event event = eventMapper.fromCreate(eventCreate);
        return eventMapper.toFullResponse(eventService.create(userId, event));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/events")
    public List<EventShortResponseDto> findAllByInitiatorId(@PathVariable int userId,
                                                           @RequestParam int from, @RequestParam int size) {
        return eventService.findAllByInitiatorId(userId, from, size).stream()
                .map(eventMapper::toShortResponse)
                .toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/events/{eventId}")
    public EventFullResponseDto findByIdAndInitiatorId(@PathVariable int userId, @PathVariable int eventId) {
        return eventMapper.toFullResponse(eventService.findEventByInitiatorId(userId, eventId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{userId}/events/{eventId}")
    public EventFullResponseDto updateEventByInitiatorId(@PathVariable int userId,
                                                         @PathVariable int eventId,
                                                         @RequestBody EventUpdateDto eventUpdate) {
        Event event = eventMapper.fromUpdate(eventUpdate);
        return eventMapper.toFullResponse(eventService.updateEventByInitiatorId(userId, eventId, event));
    }
}
