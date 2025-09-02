package com.sa.accommodation_service.room.application.ouputports.persistence;

import com.sa.accommodation_service.common.application.annotations.OutputPort;
import com.sa.accommodation_service.room.domain.Room;

@OutputPort
public interface SaveRoom {
    
    public Room save(Room room);

}
