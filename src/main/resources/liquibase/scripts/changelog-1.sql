--liquibase formatted sql

--changeset mavile:1
create table users
(
    id serial not null primary key,
    email TEXT,
    first_name  TEXT,
    last_name   TEXT,
    phone TEXT,
    role TEXT,
    image TEXT,
    user_name TEXT,
    password TEXT,
    image_id Integer
);

create table ads
(
    id serial not null primary key,
    title   TEXT,
    description TEXT,
    price Integer,
    image TEXT,
    author Integer REFERENCES users(id),
    image_id Integer

);

create table comments
(
    id serial not null primary key,
    created_at Integer,
    text TEXT,
    author Integer REFERENCES users(id),
    ad_id Integer
);

CREATE TABLE images (
    id SERIAL PRIMARY KEY,
    data bytea,
    file_path TEXT,
    file_size BIGINT,
    media_type VARCHAR
);