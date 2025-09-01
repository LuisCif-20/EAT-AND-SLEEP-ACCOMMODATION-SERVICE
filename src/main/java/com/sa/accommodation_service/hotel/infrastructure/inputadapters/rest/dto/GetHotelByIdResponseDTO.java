package com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto;

import java.util.UUID;

import com.sa.accommodation_service.hotel.domain.Hotel;

public record GetHotelByIdResponseDTO(

    UUID id,
    String name,
    String address,
    String city,
    String phoneNumber,
    String photo,
    boolean active

) {

    public static GetHotelByIdResponseDTO fromDomain(Hotel hotel) {
        return new GetHotelByIdResponseDTO(
                hotel.getId().value(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getCity(),
                hotel.getPhoneNumber().value(),
                hotel.getPhoto(),
                hotel.isActive());
    }
    
}
