--liquibase formatted sql

--changeset mavile:1
create table users
(
    id serial not null primary key,
    email VARCHAR,
    password VARCHAR,
    first_name  VARCHAR,
    last_name   VARCHAR,
    phone VARCHAR,
    role VARCHAR,
    avatar VARCHAR,
    data bytea
);

create table ads
(
    id serial not null primary key,
    title   VARCHAR,
    description VARCHAR,
    price Integer,
    image VARCHAR,
    data bytea,
    user_id Integer
);

create table comments
(
    id serial not null primary key,
    created_at TIMESTAMP,
    text VARCHAR,
    author_id Integer,
    ad_id Integer
);
