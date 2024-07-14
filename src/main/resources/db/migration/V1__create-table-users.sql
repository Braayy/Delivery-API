create table users(
    id serial primary key,

    name varchar(50) not null,
    email varchar(100) not null,
    password varchar(100) not null,

    role varchar(20) not null
);

