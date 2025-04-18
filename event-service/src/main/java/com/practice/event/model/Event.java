package com.practice.event.model;

import com.practice.category.model.Category;
import com.practice.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    EventState state;
    String title;
    String annotation;
    String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "initiator_id")
    User initiator;

    @Column(name = "created_on")
    LocalDateTime createdOn;

    @Column(name = "published_on")
    LocalDateTime publishedOn;

    @Column(name = "event_date")
    LocalDateTime eventDate;

    @Column(name = "participant_limit")
    Integer participantLimit;

    @Embedded
    @AttributeOverride(name = "lat", column = @Column(name = "location_lat"))
    @AttributeOverride(name = "lon", column = @Column(name = "location_lon"))
    Location location;

    Boolean paid;

    @Column(name = "request_moderation")
    Boolean requestModeration;

    @Column(name = "confirmed_requests")
    int confirmedRequests;

    @NotNull
    Integer views;
}
