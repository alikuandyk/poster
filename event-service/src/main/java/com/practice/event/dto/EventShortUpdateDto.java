package com.practice.event.dto;

import com.practice.event.model.ApplicationStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventShortUpdateDto {
    ApplicationStatus status;
    List<Integer> ids;
}
