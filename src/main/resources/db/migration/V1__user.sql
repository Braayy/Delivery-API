create table users(
    id serial primary key,

    name varchar(50) not null,
    email varchar(100) not null,
    password varchar(100) not null,

    active boolean not null,
    role varchar(20) not null
);

create table addresses(
    id serial primary key,

    user_id serial references users(id),

    street varchar(100) not null,
    number varchar(20),
    complement varchar(20),
    district varchar(100) not null,
    city varchar(100) not null,
    state varchar(2) not null,
    cep varchar(9) not null
);

create table cards(
    id serial primary key,

    user_id serial references users(id),

    nickname varchar(30),
    brand varchar(20) not null,
    number varchar(16) not null,
    holder_name varchar(100) not null,
    holder_cpf varchar(14) not null,
    expiration_date date not null,
    cvv varchar(100) not null
);