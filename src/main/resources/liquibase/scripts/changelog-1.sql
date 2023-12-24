--liquibase formatted sql

--changeset mavile:1
create table users
(
    id INTEGER not null primary key,
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
    id INTEGER not null primary key,
    title   TEXT,
    description TEXT,
    price Integer,
    image TEXT,
    author Integer REFERENCES users(id)
);

create table comments
(
    id INTEGER not null primary key,
    created_at Integer,
    text TEXT,
    author Integer REFERENCES users(id)
);
