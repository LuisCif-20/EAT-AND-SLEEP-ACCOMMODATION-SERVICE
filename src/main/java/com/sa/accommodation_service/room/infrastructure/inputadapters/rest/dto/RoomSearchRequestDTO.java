package com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto;

import com.sa.accommodation_service.room.application.dto.RoomSearchDTO;

public record RoomSearchRequestDTO(

    Boolean active

) {

    public RoomSearchDTO toRoomSearchDTO() {
        return new RoomSearchDTO(active);
    }
    
}
