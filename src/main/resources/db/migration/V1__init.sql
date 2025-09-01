CREATE TABLE hotel (
    id UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    address VARCHAR(200) NOT NULL,
    city VARCHAR(100) NOT NULL,
    phone_number VARCHAR(9) NOT NULL,
    photo VARCHAR NOT NULL,
    active BOOLEAN NOT NULL
);