package com.sa.accommodation_service.hotel.domain;

import java.util.UUID;

import com.sa.accommodation_service.common.domain.annotations.Default;
import com.sa.accommodation_service.common.domain.annotations.DomainEntity;
import com.sa.accommodation_service.common.domain.valueobjects.Id;
import com.sa.accommodation_service.hotel.domain.valueobjects.PhoneNumber;

import lombok.Getter;

@Getter
@DomainEntity
public class Hotel {
    
    private Id id;
    private String name;
    private String address;
    private String city;
    private PhoneNumber phoneNumber;
    private String photo;
    private boolean active;

    @Default
    public Hotel(UUID id, String name, String address, String city, String phoneNumber, String photo, boolean active) {
        this.id = new Id(id);
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.photo = photo;
        this.active = active; 
    }

    public Hotel(String name, String address, String city, String phoneNumber, String photo) {
        this.id = Id.generate();
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.photo = photo;
        this.active = true;
    }

}
