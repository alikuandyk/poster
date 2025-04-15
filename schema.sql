create table users
(
    id    serial8 primary key,
    name  varchar(250) not null,
    email varchar(254) not null unique
);

create table category
(
    id   serial8 primary key,
    name varchar(50) not null unique
);

create table events
(
    id                 serial primary key,
    state              varchar                       not null,
    title              varchar(120)                  not null,
    annotation         varchar(2000)                 not null,
    description        varchar(7000),
    category_id        int8 references category (id) not null,
    initiator_id       int8 references users (id)    not null,
    created_on         timestamp,
    published_on       timestamp,
    event_date         timestamp                     not null,
    participant_limit  int8    default 0,
    location_lat       float8                        not null,
    location_lon       float8                        not null,
    paid               boolean                       not null,
    request_moderation boolean default true,
    confirmed_requests int8                          not null,
    views              int8                          not null
);

create table compilations
(
    id     serial8 primary key,
    title  varchar(50) not null,
    pinned boolean     not null
);

create table compilations_events
(
    compilation_id int8 references compilations (id) not null,
    event_id       int8 references events (id)       not null,
    primary key (compilation_id, event_id)
);

create table participation_requests
(
    id           serial8 primary key,
    requester_id int8 references users (id)  not null,
    event_id     int8 references events (id) not null,
    created      timestamp default now(),
    status       varchar                     not null,
    constraint unique_request unique (requester_id, event_id)
);