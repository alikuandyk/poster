package com.practice.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.event.model.EventStateAction;
import com.practice.event.model.Location;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullUpdateDto {
    EventStateAction stateAction;

    @Size(min = 3, max = 120)
    String title;

    @Size(min = 20, max = 2000)
    String annotation;

    @Size(min = 20, max = 7000)
    String description;

    Integer category;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Future(message = "Дата событии не может быть раньше, чем через два часа от текущего момента")
    LocalDateTime eventDate;

    @PositiveOrZero
    Integer participantLimit;

    @Valid
    Location location;

    Boolean paid;
    Boolean requestModeration;
}
