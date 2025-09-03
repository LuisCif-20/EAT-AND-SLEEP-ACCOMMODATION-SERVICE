package com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.sa.accommodation_service.room.domain.Room;

public record ShortRoomResponse(

    UUID id,
    String roomNumber,
    BigDecimal pricePerNight,
    String photo,
    Boolean available

) {

    public static ShortRoomResponse fromDomain(Room room) {
        return new ShortRoomResponse(
                room.getId().value(),
                room.getRoomNumber().value(),
                room.getPricePerNight().value(),
                room.getPhoto(),
                room.isAvailable());
    }
    
}
