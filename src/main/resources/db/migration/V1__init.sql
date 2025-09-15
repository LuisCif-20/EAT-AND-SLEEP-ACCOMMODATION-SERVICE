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
    room_number VARCHAR(10) NOT NULL,
    description TEXT NOT NULL,
    price_per_night DECIMAL(10, 2) NOT NULL,
    maintenance_cost DECIMAL(10, 2) NOT NULL,
    photo VARCHAR NOT NULL,
    active BOOLEAN NOT NULL,
    UNIQUE (room_number, hotel_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(id)
);

CREATE TABLE lodging (
    id UUID PRIMARY KEY,
    room_id UUID NOT NULL,
    customer_id UUID NOT NULL,
    check_in TIMESTAMP NOT NULL,
    check_out TIMESTAMP NOT NULL,
    status VARCHAR(15) NOT NULL CHECK (status IN ('RESERVED', 'ONGOING', 'COMPLETED', 'CANCELLED')),
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room(id)
);