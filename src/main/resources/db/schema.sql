create table if not exists users
(
    id       bigserial primary key,
    fullname varchar(255) not null,
    age      int          not null
);
create table if not exists pets
(
    id      bigserial primary key,
    name    varchar(50) not null,
    age     int         not null,
    breed   varchar(50) not null,
    user_id bigint references users (id)
);