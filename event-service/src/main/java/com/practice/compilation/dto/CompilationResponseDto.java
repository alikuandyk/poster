package com.practice.compilation.dto;

import com.practice.event.dto.EventShortResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationResponseDto {
    @NotBlank
    Integer id;

    @NotBlank
    String title;

    @NotBlank
    boolean pinned;

    List<EventShortResponseDto> events;
}
