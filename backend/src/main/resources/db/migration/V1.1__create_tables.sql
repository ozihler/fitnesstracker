CREATE TABLE WORKOUT(
    id serial primary key,
    workout_id varchar(255) unique not null,
    creation_date date not null,
    is_deleted boolean
);

CREATE TABLE MUSCLE_GROUP(
    id serial primary key,
    name varchar(255) unique not null,
    is_selectable boolean,
    workout_id bigint,
    foreign key (workout_id) references workout (id)
);

CREATE TABLE EXERCISE(
    id serial primary key,
    name varchar(255) unique not null,
    multiplier integer not null,
    is_selectable boolean,
    muscle_group_id bigint not null,
    foreign key (muscle_group_id) references muscle_group (id)
);

CREATE TABLE SET(
    id serial primary key,
    weight real not null,
    weight_unit varchar(255) not null,
    repetitions integer not null,
    waiting_time integer,
    waiting_time_unit varchar(255),
    exercise_id bigint not null,
    foreign key (exercise_id) references exercise (id)
);
