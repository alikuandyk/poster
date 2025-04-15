package com.practice.request.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestsResponseDto {
    List<RequestResponseDto> confirmedRequests;
    List<RequestResponseDto> rejectedRequests;
}
