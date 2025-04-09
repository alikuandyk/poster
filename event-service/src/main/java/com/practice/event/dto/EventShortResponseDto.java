package com.practice.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.category.model.CategoryResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventShortResponseDto {
    int id;

    String title;
    String annotation;

    CategoryResponseDto category;

    UserShortDto initiator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    Boolean paid;

    Integer confirmedRequests;
    Integer views;
}
