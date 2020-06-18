create table set(
    id integer not null primary key,
    weight real not null,
    weight_unit varchar(128) not null,
    repetitions integer not null,
    waiting_time integer,
    waiting_time_unit varchar(128)
);
