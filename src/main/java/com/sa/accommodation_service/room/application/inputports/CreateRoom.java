package com.sa.accommodation_service.room.application.inputports;

import com.sa.accommodation_service.common.application.annotations.InputPort;
import com.sa.accommodation_service.room.application.dto.CreateRoomDTO;
import com.sa.accommodation_service.room.domain.Room;

import jakarta.validation.Valid;

@InputPort
public interface CreateRoom {
    
    public Room create(@Valid CreateRoomDTO createRoomDTO);

}
