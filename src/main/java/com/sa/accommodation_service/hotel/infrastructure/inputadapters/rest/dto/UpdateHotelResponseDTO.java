package com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto;

import java.util.UUID;

import com.sa.accommodation_service.hotel.domain.Hotel;

public record UpdateHotelResponseDTO(

    UUID id

) {
    
    public static UpdateHotelResponseDTO fromDomain(Hotel hotel) {
        return new UpdateHotelResponseDTO(hotel.getId().value());
    }

}
