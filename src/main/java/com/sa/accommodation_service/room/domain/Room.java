package com.sa.accommodation_service.room.domain;

import java.math.BigDecimal;
import java.util.UUID;

import com.sa.accommodation_service.common.domain.annotations.Default;
import com.sa.accommodation_service.common.domain.annotations.DomainEntity;
import com.sa.accommodation_service.common.domain.valueobjects.Id;
import com.sa.accommodation_service.common.domain.valueobjects.Money;
import com.sa.accommodation_service.hotel.domain.Hotel;
import com.sa.accommodation_service.room.domain.valueobjects.RoomNumber;

import lombok.Getter;

@Getter
@DomainEntity
public class Room {

    private Id id;
    private Hotel hotel;
    private RoomNumber roomNumber;
    private String description;
    private Money pricePerNight;
    private Money maintenanceCost;
    private boolean available;
    private boolean active;
    private String photo;

    @Default
    public Room(UUID id, Hotel hotel, String roomNumber, String description,
            BigDecimal pricePerNight, BigDecimal maintenanceCost, 
            boolean available, boolean active, String photo) {
        this.id = new Id(id);
        this.hotel = hotel;
        this.roomNumber = new RoomNumber(roomNumber);
        this.description = description;
        this.pricePerNight = new Money(pricePerNight);
        this.maintenanceCost = new Money(maintenanceCost);
        this.available = available;
        this.active = active;
        this.photo = photo;
    }

    public Room(Hotel hotel, String roomNumber, String description,
            BigDecimal pricePerNight, BigDecimal maintenanceCost, String photo) {
        this.id = Id.generate();
        this.hotel = hotel;
        this.roomNumber = new RoomNumber(roomNumber);
        this.description = description;
        this.pricePerNight = new Money(pricePerNight);
        this.maintenanceCost = new Money(maintenanceCost);
        this.available = true;
        this.active = true;
        this.photo = photo;
    }

    public void markAsAvailable() {
        this.available = true;
    }

    public void markAsOccupied() {
        this.available = false;
    }

}
