package com.practice.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.category.model.CategoryResponseDto;
import com.practice.event.model.EventState;
import com.practice.event.model.Location;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullResponseDto {
    int id;

    EventState state;
    String title;
    String annotation;
    String description;

    CategoryResponseDto category;

    UserShortDto initiator;

    LocalDateTime createdOn;

    LocalDateTime publishedOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    Integer participantLimit;

    Location location;

    Boolean paid;

    Boolean requestModeration;


    Integer confirmedRequests;

    Integer views;
}
