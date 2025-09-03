package com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto;

import java.util.UUID;

import com.sa.accommodation_service.hotel.domain.Hotel;

public record ShortHotelResponse(

    UUID id,
    String name,
    String address,
    String city,
    String photo

) {

    public static ShortHotelResponse fromDomain(Hotel hotel) {
        return new ShortHotelResponse(hotel.getId().value(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getCity(),
                hotel.getPhoto());
    }
    
}
