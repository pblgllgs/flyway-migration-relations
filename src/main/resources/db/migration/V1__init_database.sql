create sequence id_address_sequence_generator;

alter sequence id_address_sequence_generator owner to postgres;

create sequence id_department_sequence_generator;

alter sequence id_department_sequence_generator owner to postgres;

create sequence id_employee_sequence_generator;

alter sequence id_employee_sequence_generator owner to postgres;

create sequence id_mission_sequence_generator;

alter sequence id_mission_sequence_generator owner to postgres;

create table if not exists address
(
    id           integer      not null
        primary key,
    house_number varchar(255) not null,
    street_name  varchar(255) not null,
    zip_code     varchar(255) not null
);

alter table address
    owner to postgres;

create table if not exists departments
(
    id   integer not null
        primary key,
    name varchar(255)
);

alter table departments
    owner to postgres;

create table if not exists employees
(
    id            integer      not null
        primary key,
    birthdate     date         not null,
    email         varchar(255) not null
        constraint email_unique_constraint
            unique,
    firstname     varchar(255) not null,
    identifier    varchar(255) not null
        constraint identifier_unique_constraint
            unique,
    lastname      varchar(255) not null,
    role          varchar(255),
    address_id    integer
        constraint address_id_foreign_key
            references address,
    department_id integer
        constraint department_id_foreign_key
            references departments
);

alter table employees
    owner to postgres;

create table if not exists missions
(
    id       integer      not null
        primary key,
    duration integer      not null,
    name     varchar(255) not null
);

alter table missions
    owner to postgres;

create table if not exists employee_mission
(
    employee_id integer not null
        constraint employee_id_foreign_key
            references employees,
    mission_id  integer not null
        constraint mission_id_foreign_key
            references missions
);

alter table employee_mission
    owner to postgres;

