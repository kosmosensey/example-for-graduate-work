--liquibase formatted sql

--changeset mavile:1
create table users
(
    id bigserial not null primary key,
    email varchar(50),
    first_name  varchar(255),
    last_name   varchar(255),
    phone varchar(11),
    role varchar(10),
    image varchar(255)
);
create table ads
(
    pk bigserial not null primary key,
    author_id bigint,
    title   varchar(255),
    price int,
    image varchar(255),
    FOREIGN KEY (author_id) REFERENCES users(id)
);
create table comments
(
    pk bigserial not null primary key,
    author bigint,
    author_image varchar(255),
    author_first_name varchar(255),
    created_at timestamp,
    text varchar(255),
    FOREIGN KEY (author) REFERENCES users(id)
);

