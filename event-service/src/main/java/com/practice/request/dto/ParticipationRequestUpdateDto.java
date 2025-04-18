package com.practice.request.dto;

import com.practice.request.model.RequestStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParticipationRequestUpdateDto {
    List<Integer> requestIds;
    RequestStatus status;
}
