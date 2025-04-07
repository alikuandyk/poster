package com.practice.event.model;

import com.practice.category.model.Category;
import com.practice.category.model.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.practice.event.model.EventFullResponseDto.*;

@Component
@RequiredArgsConstructor
public class EventMapper {
    public Event fromCreate(EventCreateDto eventCreate) {
        Event event = new Event();

        Category category = new Category();
        category.setId(eventCreate.getCategory());

        event.setTitle(eventCreate.getTitle());
        event.setAnnotation(eventCreate.getAnnotation());
        event.setDescription(eventCreate.getDescription());
        event.setCategory(category);
        event.setEventDate(eventCreate.getEventDate());
        event.setLocation(eventCreate.getLocation());
        event.setParticipantLimit(eventCreate.getParticipantLimit());
        event.setPaid(eventCreate.getPaid());
        event.setRequestModeration(eventCreate.getRequestModeration());

        return event;
    }

    public EventFullResponseDto toFullResponse(Event event) {
        EventFullResponseDto eventFullResponse = new EventFullResponseDto();

        CategoryResponseDto categoryResponse = new CategoryResponseDto();
        categoryResponse.setId(event.getCategory().getId());
        categoryResponse.setName(event.getCategory().getName());

        UserShortDto userShort = new UserShortDto();
        userShort.setId(event.getInitiator().getId());
        userShort.setName(event.getInitiator().getName());

        eventFullResponse.setId(event.getId());
        eventFullResponse.setState(event.getState());
        eventFullResponse.setTitle(event.getTitle());
        eventFullResponse.setAnnotation(event.getAnnotation());
        eventFullResponse.setDescription(event.getDescription());
        eventFullResponse.setCategory(categoryResponse);
        eventFullResponse.setInitiator(userShort);
        eventFullResponse.setCreatedOn(event.getCreatedOn());
        eventFullResponse.setPublishedOn(event.getPublishedOn());
        eventFullResponse.setEventDate(event.getEventDate());
        eventFullResponse.setParticipantLimit(event.getParticipantLimit());
        eventFullResponse.setLocation(event.getLocation());
        eventFullResponse.setPaid(event.getPaid());
        eventFullResponse.setRequestModeration(event.getRequestModeration());
        eventFullResponse.setViews(event.getViews());

        return eventFullResponse;
    }
}
