package com.practice.event.dto;

import com.practice.category.model.Category;
import com.practice.category.model.CategoryResponseDto;
import com.practice.event.model.Event;
import com.practice.event.model.EventState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

    public Event fromUpdate(EventUpdateDto eventUpdate) {
        Event event = new Event();

        if (eventUpdate.getCategory() != null) {
            Category category = new Category();
            category.setId(eventUpdate.getCategory());
            event.setCategory(category);
        }

        switch (eventUpdate.getStateAction()) {
            case SEND_TO_REVIEW -> event.setState(EventState.PENDING);
            case CANCEL_REVIEW, REJECT_EVENT -> event.setState(EventState.CANCELED);
            case PUBLISH_EVENT -> event.setState(EventState.PUBLISHED);
        }

        event.setTitle(eventUpdate.getTitle());
        event.setAnnotation(eventUpdate.getAnnotation());
        event.setDescription(eventUpdate.getDescription());
        event.setEventDate(eventUpdate.getEventDate());
        event.setParticipantLimit(eventUpdate.getParticipantLimit());
        event.setLocation(eventUpdate.getLocation());
        event.setPaid(eventUpdate.getPaid());
        event.setRequestModeration(eventUpdate.getRequestModeration());

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

    public EventShortResponseDto toShortResponse(Event event) {
        EventShortResponseDto eventShortResponse = new EventShortResponseDto();

        CategoryResponseDto categoryResponse = new CategoryResponseDto();
        categoryResponse.setId(event.getCategory().getId());
        categoryResponse.setName(event.getCategory().getName());

        UserShortDto userShort = new UserShortDto();
        userShort.setId(event.getInitiator().getId());
        userShort.setName(event.getInitiator().getName());

        eventShortResponse.setId(event.getId());
        eventShortResponse.setTitle(event.getTitle());
        eventShortResponse.setAnnotation(event.getAnnotation());
        eventShortResponse.setCategory(categoryResponse);
        eventShortResponse.setInitiator(userShort);
        eventShortResponse.setPaid(event.getPaid());
        eventShortResponse.setConfirmedRequests(event.getConfirmedRequests());
        eventShortResponse.setViews(event.getViews());

        return eventShortResponse;
    }
}
