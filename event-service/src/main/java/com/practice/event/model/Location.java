package com.practice.event.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class Location {
    @NotNull
    Double lat;

    @NotNull
    Double lon;
}
