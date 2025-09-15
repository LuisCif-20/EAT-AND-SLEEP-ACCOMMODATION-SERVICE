package com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto.ShortHotelResponse;
import com.sa.accommodation_service.room.domain.Room;

public record GetRoomByIdResponseDTO(

    UUID id,
    ShortHotelResponse hotel,
    String roomNumber,
    String description,
    BigDecimal pricePerNight,
    BigDecimal maintenanceCost,
    String photo,
    boolean active

) {

    public static GetRoomByIdResponseDTO fromDomain(Room room) {
        return new GetRoomByIdResponseDTO(
                room.getId().value(),
                ShortHotelResponse.fromDomain(room.getHotel()),
                room.getRoomNumber().value(),
                room.getDescription(),
                room.getPricePerNight().value(),
                room.getMaintenanceCost().value(),
                room.getPhoto(),
                room.isActive());
    }
    
}
