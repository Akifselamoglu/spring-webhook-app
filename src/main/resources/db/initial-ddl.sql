create table "user"
(
    id            bigserial not null
        constraint "user_pkey"
            primary key,
    create_time   timestamptz,
    created_by    bigint,
    update_time   timestamptz,
    updated_by    bigint,
    version       bigint,
    name     varchar(255),
    email varchar(255) unique,
    phone_number varchar(255)
);

create table "contact"
(
    id            bigserial not null
        constraint "contact_pkey"
            primary key,
    create_time   timestamptz,
    created_by    bigint,
    update_time   timestamptz,
    updated_by    bigint,
    version       bigint,
    name     varchar(255) not null,
    email varchar(255) unique not null,
    phone_number varchar(255),
    user_id bigint not null
);

create table "message"
(
    id            bigserial not null
        constraint "message_pkey"
            primary key,
    create_time   timestamptz,
    created_by    bigint,
    update_time   timestamptz,
    updated_by    bigint,
    version       bigint,
    text     varchar(2550) not null,
    message_direction varchar(255),
    message_source varchar(255),
    ip_address varchar(255),
    user_id bigint not null,
    contact_id bigint
);

create table "webhook"
(
    id            bigserial not null
        constraint "webhook_pkey"
            primary key,
    create_time   timestamptz,
    created_by    bigint,
    update_time   timestamptz,
    updated_by    bigint,
    version       bigint,
    secret_key     varchar(255) unique not null,
    user_id bigint not null,
    status boolean not null
);

create table "blacklist"
(
    id            bigserial not null
        constraint "whitelist_pkey"
            primary key,
    ip_address varchar(255),
    status boolean
);