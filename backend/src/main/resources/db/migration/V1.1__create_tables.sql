DROP TABLE IF EXISTS WORKOUT, MUSCLE_GROUP, EXERCISE, SET;

CREATE TABLE WORKOUT
(
    id            serial primary key,
    workout_id    varchar(255) unique not null,
    creation_date date                not null,
    is_deleted    boolean             not null
);

CREATE TABLE MUSCLE_GROUP
(
    id            serial primary key,
    name          varchar(255) unique not null,
    is_selectable boolean             not null
);

CREATE TABLE WORKOUT_TO_MUSCLE_GROUP
(
    workout_id      bigint not null,
    muscle_group_id bigint not null,
    primary key (workout_id, muscle_group_id),
    foreign key (workout_id) references workout (id),
    foreign key (muscle_group_id) references muscle_group (id)
);

CREATE TABLE EXERCISE
(
    id              serial unique       not null,
    name            varchar(255) unique not null,
    multiplier      integer             not null,
    is_selectable   boolean             not null,
    muscle_group_id bigint              not null,
    foreign key (muscle_group_id) references muscle_group (id),
    primary key (id, muscle_group_id)
);

CREATE TABLE SET
(
    id                       serial       not null unique,
    weight                   real         not null,
    weight_unit              varchar(255) not null,
    repetitions              integer      not null,
    waiting_time             integer,
    waiting_time_unit        varchar(255),
    workout_id               bigint       not null,
    exercise_id              bigint       not null,
    exercise_muscle_group_id bigint       not null,
    foreign key (workout_id) references WORKOUT (id),
    foreign key (exercise_id, exercise_muscle_group_id) references exercise (id, muscle_group_id),
    primary key (id, workout_id, exercise_id, exercise_muscle_group_id)

);
