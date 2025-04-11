package com.practice.event.dto;

import com.practice.event.model.EventStateAction;
import com.practice.event.model.Location;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventUpdateDto {
    EventStateAction stateAction;
    String title;
    String annotation;
    String description;

    Integer category;

    LocalDateTime eventDate;
    Integer participantLimit;

    Location location;
    Boolean paid;
    Boolean requestModeration;
}
