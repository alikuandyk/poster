package com.practice.event.model;

import com.practice.category.model.CategoryResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullResponseDto {
    int id;

    State state;
    String title;
    String annotation;
    String description;

    CategoryResponseDto category;

    UserShortDto initiator;

    LocalDateTime createdOn;

    LocalDateTime publishedOn;

    LocalDateTime eventDate;

    Integer participantLimit;

    Location location;

    Boolean paid;

    Boolean requestModeration;


    Integer confirmedRequests;

    Integer views;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UserShortDto {
        int id;
        String name;
    }
}
