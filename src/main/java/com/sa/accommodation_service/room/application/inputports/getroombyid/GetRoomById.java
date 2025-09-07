package com.sa.accommodation_service.room.application.inputports.getroombyid;

import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.InputPort;
import com.sa.accommodation_service.room.domain.Room;

@InputPort
public interface GetRoomById {
    
    public Room getById(UUID id);

}
