package com.practice.request.dto;

import com.practice.request.model.ApplicationStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestResponseDto {
    int id;
    int requester;
    int event;
    ApplicationStatus status;
    LocalDateTime created;
}
