--liquibase formatted sql

--changeset mavile:1
create table users
(
    id INTEGER not null primary key,
    email varchar(50),
    first_name  varchar(255),
    last_name   varchar(255),
    phone varchar(11),
    role varchar(10),
    image varchar(255),
    user_name varchar(255),
    password varchar(255)
);

create table ads
(
    pk INTEGER not null primary key,
    author_id Integer,
    title   varchar(255),
    description varchar(255),
    price Integer,
    image varchar(255),
    FOREIGN KEY (author_id) REFERENCES users(id)
);

create table comments
(
    pk INTEGER not null primary key,
    description varchar(255),
    author Integer,
    author_image varchar(255),
    author_first_name varchar(255),
    created_at Integer,
    text varchar(255),
    FOREIGN KEY (author) REFERENCES users(id)
);

