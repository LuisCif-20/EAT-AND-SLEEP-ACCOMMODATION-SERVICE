package com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto.HotelResponse;
import com.sa.accommodation_service.room.domain.Room;

public record GetRoomByIdResponseDTO(

    UUID id,
    HotelResponse hotel,
    String roomNumber,
    String description,
    BigDecimal pricePerNight,
    BigDecimal maintenanceCost,
    String photo,
    boolean available,
    boolean active

) {

    public static GetRoomByIdResponseDTO fromDomain(Room room) {
        return new GetRoomByIdResponseDTO(
                room.getId().value(),
                HotelResponse.fromDomain(room.getHotel()),
                room.getRoomNumber().value(),
                room.getDescription(),
                room.getPricePerNight().value(),
                room.getMaintenanceCost().value(),
                room.getPhoto(),
                room.isAvailable(),
                room.isActive());
    }
    
}
