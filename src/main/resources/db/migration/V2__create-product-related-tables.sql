create table products(
    id serial primary key,

    name varchar(50) not null unique,
    description text,
    image_url text,
    price decimal(7, 2) not null,
    quantity_type varchar(10) not null,
    weight_multiplier integer,
    max_additionals smallint not null
);

create table product_additionals(
    id serial primary key,

    product_id serial references products(id),

    name varchar(50) not null,
    description text,
    price decimal(7, 2) not null
);