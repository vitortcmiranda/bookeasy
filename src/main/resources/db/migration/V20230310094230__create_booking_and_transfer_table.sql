CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE booking
(
    id               uuid         NOT NULL DEFAULT uuid_generate_v4(),
    accommodation_id uuid NULL,
    transfer_id      uuid NULL,
    first_name       varchar(255) NOT NULL,
    last_name        varchar(255) NOT NULL,
    email            varchar(255) NOT NULL,
    phone_number     varchar(255) NOT NULL,
    created_at       timestamp    NOT NULL DEFAULT now(),
    updated_at       timestamp    NOT NULL DEFAULT now(),
    amount           numeric(10, 2) NULL,
    CONSTRAINT bookings_pkey PRIMARY KEY (id)
);

CREATE TABLE transfer
(
    id          uuid    NOT NULL,
    origin      varchar NOT NULL,
    destination varchar NOT NULL,
    seat        varchar NULL,
    price       numeric(10, 2) NULL,
    "type"      varchar NULL,
    booking_id  uuid NULL,
    CONSTRAINT transfer_pk PRIMARY KEY (id)
);