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
    private String photo;
    private boolean available;
    private boolean active;

    @Default
    public Room(UUID id, Hotel hotel, String roomNumber, String description,
            BigDecimal pricePerNight, BigDecimal maintenanceCost, 
            String photo, boolean available, boolean active) {
        this.id = new Id(id);
        this.hotel = hotel;
        this.roomNumber = new RoomNumber(roomNumber);
        this.description = description;
        this.pricePerNight = new Money(pricePerNight);
        this.maintenanceCost = new Money(maintenanceCost);
        this.photo = photo;
        this.available = available;
        this.active = active;
    }

    public Room(Hotel hotel, String roomNumber, String description,
            BigDecimal pricePerNight, BigDecimal maintenanceCost, String photo) {
        this.id = new Id(UUID.randomUUID());
        this.hotel = hotel;
        this.roomNumber = new RoomNumber(roomNumber);
        this.description = description;
        this.pricePerNight = new Money(pricePerNight);
        this.maintenanceCost = new Money(maintenanceCost);
        this.photo = photo;
        this.available = true;
        this.active = true;
    }

    public void toggleAvailable() {
        this.available = !this.available;
    }

    public void toggleActive() {
        this.active = !this.active;
    }
    
}
