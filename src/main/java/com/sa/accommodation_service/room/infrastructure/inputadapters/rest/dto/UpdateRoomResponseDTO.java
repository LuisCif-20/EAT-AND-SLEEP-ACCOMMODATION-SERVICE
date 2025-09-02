package com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto;

import java.util.UUID;

import com.sa.accommodation_service.room.domain.Room;

public record UpdateRoomResponseDTO(

    UUID id

) {
    
    public static UpdateRoomResponseDTO fromDomain(Room room) {
        return new UpdateRoomResponseDTO(room.getId().value());
    }

}
