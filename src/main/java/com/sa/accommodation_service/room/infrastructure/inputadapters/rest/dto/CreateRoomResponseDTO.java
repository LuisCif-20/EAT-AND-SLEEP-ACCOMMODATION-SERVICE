package com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto;

import java.util.UUID;

import com.sa.accommodation_service.room.domain.Room;

public record CreateRoomResponseDTO(

    UUID id

) {

    public static CreateRoomResponseDTO fromDomain(Room room) {
        return new CreateRoomResponseDTO(room.getId().value());
    }
    
}
