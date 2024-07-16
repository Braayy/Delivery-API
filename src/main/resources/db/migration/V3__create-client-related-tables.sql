create table addresses(
    id serial primary key,

    user_id serial references users(id),

    street varchar(100) not null,
    number varchar(20) not null,
    complement varchar(20) not null,
    district varchar(100) not null,
    city varchar(100) not null,
    state varchar(2) not null,
    cep varchar(9) not null
);

create table cards(
    id serial primary key,

    user_id serial references users(id),

    brand varchar(20) not null,

    number varchar(16) not null,
    holder_name varchar(100) not null,
    holder_cpf varchar(100) not null,
    expiration_date date not null,
    cvv varchar(100) not null
);