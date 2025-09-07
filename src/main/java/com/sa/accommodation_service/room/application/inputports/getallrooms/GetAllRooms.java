package com.sa.accommodation_service.room.application.inputports.getallrooms;

import java.util.List;

import com.sa.accommodation_service.common.application.annotations.InputPort;
import com.sa.accommodation_service.room.application.inputports.getallrooms.dto.RoomSearchDTO;
import com.sa.accommodation_service.room.domain.Room;

import jakarta.validation.Valid;

@InputPort
public interface GetAllRooms {
    
    public List<Room> getAll(@Valid RoomSearchDTO roomSearchDTO);

}
