package com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto;

import java.util.UUID;

import com.sa.accommodation_service.hotel.domain.Hotel;

public record CreateHotelResponseDTO(

    UUID id

) {
    
    public static CreateHotelResponseDTO fromDomain(Hotel hotel) {
        return new CreateHotelResponseDTO(hotel.getId().value());
    }

}
