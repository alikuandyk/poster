package com.practice.controller.personal;

import com.practice.event.model.Event;
import com.practice.event.dto.EventCreateDto;
import com.practice.event.dto.EventFullResponseDto;
import com.practice.event.dto.EventMapper;
import com.practice.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
