CREATE TABLE hotel (
    id UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    address VARCHAR(200) NOT NULL,
    city VARCHAR(100) NOT NULL,
    phone_number VARCHAR(9) NOT NULL,
    photo VARCHAR NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE room (
    id UUID PRIMARY KEY,
    hotel_id UUID NOT NULL,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    description TEXT NOT NULL,
    price_per_night DECIMAL(10, 2) NOT NULL,
    maintenance_cost DECIMAL(10, 2) NOT NULL,
    photo VARCHAR NOT NULL,
    available BOOLEAN NOT NULL,
    active BOOLEAN NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES hotel(id)
);