package com.practice.request.dto;

import com.practice.request.model.ApplicationStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestUpdateDto {
    List<Integer> requestIds;
    ApplicationStatus status;
}
