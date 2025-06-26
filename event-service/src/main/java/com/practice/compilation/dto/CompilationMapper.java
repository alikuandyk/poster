package com.practice.compilation.dto;

import com.practice.category.model.CategoryResponseDto;
import com.practice.compilation.model.Compilation;
import com.practice.event.dto.EventShortResponseDto;
import com.practice.event.dto.UserShortDto;
import com.practice.event.model.Event;
import com.practice.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompilationMapper {
    private final EventRepository eventRepository;

    public Compilation fromCreate(CompilationCreateDto compilationCreate) {
        Compilation compilation = new Compilation();

        compilation.setTitle(compilationCreate.getTitle() != null ? compilationCreate.getTitle() : "");
        compilation.setPinned(compilationCreate.getPinned() != null && compilationCreate.getPinned());
        compilation.setEvents(findAllEventsByIds(compilationCreate.getEventIds()));

        return compilation;
    }

    public Compilation fromUpdate(CompilationUpdateDto compilationUpdate) {
        Compilation compilation = new Compilation();

        compilation.setTitle(compilationUpdate.getTitle());
        compilation.setPinned(compilationUpdate.getPinned());
        compilation.setEvents(findAllEventsByIds(compilationUpdate.getEventIds()));

        return compilation;
    }

    public CompilationResponseDto toResponse(Compilation compilation) {
        CompilationResponseDto compilationResponse = new CompilationResponseDto();

        List<EventShortResponseDto> eventShortResponses = compilation.getEvents() == null
                ? Collections.emptyList()
                : compilation.getEvents().stream()
                .filter(event -> event != null && event.getId() != null)
                .map(event -> {
                    EventShortResponseDto eventShortResponse = new EventShortResponseDto();
                    eventShortResponse.setId(event.getId());
                    eventShortResponse.setTitle(event.getTitle());
                    eventShortResponse.setAnnotation(event.getAnnotation());
                    eventShortResponse.setEventDate(event.getEventDate());
                    eventShortResponse.setPaid(event.getPaid());
                    eventShortResponse.setConfirmedRequests(event.getConfirmedRequests());
                    eventShortResponse.setViews(event.getViews());

                    if (event.getCategory() != null) {
                        CategoryResponseDto categoryDto = new CategoryResponseDto();
                        categoryDto.setId(event.getCategory().getId());
                        categoryDto.setName(event.getCategory().getName());
                        eventShortResponse.setCategory(categoryDto);
                    }

                    if (event.getInitiator() != null) {
                        UserShortDto userDto = new UserShortDto();
                        userDto.setId(event.getInitiator().getId());
                        userDto.setName(event.getInitiator().getName());
                        eventShortResponse.setInitiator(userDto);
                    }

                    return eventShortResponse;
                })
                .toList();

        compilationResponse.setId(compilation.getId());
        compilationResponse.setTitle(compilation.getTitle());
        compilationResponse.setPinned(compilation.getPinned());
        compilationResponse.setEvents(eventShortResponses);

        return compilationResponse;
    }

    public List<Event> findAllEventsByIds(List<Integer> eventIds) {
        if (eventIds == null || eventIds.isEmpty()) {
            return Collections.emptyList();
        }
        return eventRepository.findAllById(eventIds);
    }
 }
