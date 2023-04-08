CREATE TABLE accomodation
(
    booking_id  uuid NULL,
    id          uuid NULL,
    description varchar(255) NULL,
    "location"  varchar(255) NOT NULL,
    price       numeric(10, 2) NULL,
    created_at  timestamp NOT NULL DEFAULT now(),
    updated_at  timestamp NOT NULL DEFAULT now(),
    CONSTRAINT accomodation_pk PRIMARY KEY (id),
    CONSTRAINT accomodation_fk FOREIGN KEY (booking_id) REFERENCES booking (id)
);