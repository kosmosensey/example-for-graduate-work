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
    password TEXT
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
    author Integer REFERENCES users(id)
);

CREATE TABLE image (
    id SERIAL PRIMARY KEY,
    data oid,
    file_size BIGINT,
    media_type VARCHAR
);