package com.sa.accommodation_service.room.application.inputports.updateroom;

import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.InputPort;
import com.sa.accommodation_service.room.application.inputports.updateroom.dto.UpdateRoomDTO;
import com.sa.accommodation_service.room.domain.Room;

import jakarta.validation.Valid;

@InputPort
public interface UpdateRoom {
    
    public Room update(UUID id, @Valid UpdateRoomDTO updateRoomDTO);

}
