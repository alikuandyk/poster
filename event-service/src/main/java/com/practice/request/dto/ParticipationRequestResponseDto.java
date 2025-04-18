package com.practice.request.dto;

import com.practice.request.model.RequestStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParticipationRequestResponseDto {
    int id;
    int requester;
    int event;
    RequestStatus status;
    LocalDateTime created;
}
