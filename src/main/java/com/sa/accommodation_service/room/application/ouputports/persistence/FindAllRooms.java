package com.sa.accommodation_service.room.application.ouputports.persistence;

import java.util.List;

import com.sa.accommodation_service.common.application.annotations.OutputPort;
import com.sa.accommodation_service.room.application.dto.RoomSearchDTO;
import com.sa.accommodation_service.room.domain.Room;

@OutputPort
public interface FindAllRooms {
    
    public List<Room> findAll(RoomSearchDTO roomSearchDTO);

}
