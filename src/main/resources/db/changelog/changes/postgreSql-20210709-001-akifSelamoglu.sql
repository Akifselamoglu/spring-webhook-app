create table "user"
(
    id            bigint not null
        constraint "user_pkey"
            primary key,
    create_date   date,
    created_by    bigint,
    update_date   date,
    updated_by    bigint,
    version       bigint,
    name     varchar(255),
    email varchar(255),
    phone_number varchar(255)
);

create table "contact"
(
    id            bigint not null
        constraint "contact_pkey"
            primary key,
    create_date   date,
    created_by    bigint,
    update_date   date,
    updated_by    bigint,
    version       bigint,
    name     varchar(255) not null,
    email varchar(255) not null,
    phone_number varchar(255),
    user_id bigint not null
);

create table "message"
(
    id            bigint not null
        constraint "message_pkey"
            primary key,
    create_date   date,
    created_by    bigint,
    update_date   date,
    updated_by    bigint,
    version       bigint,
    text     varchar(2550) not null,
    message_direction varchar(255),
    message_source varchar(255),
    ip_address varchar(255),
    user_id bigint not null,
    contact_id bigint not null
);

create table "webhook"
(
    id            bigint not null
        constraint "webhook_pkey"
            primary key,
    create_date   date,
    created_by    bigint,
    update_date   date,
    updated_by    bigint,
    version       bigint,
    secretKey     varchar(255) not null,
    user_id bigint not null,
    status boolean not null
);

create table "whitelist"
(
    id            bigint not null
        constraint "whitelist_pkey"
            primary key,
    ip_address varchar(255),
    status boolean
);