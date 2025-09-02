package com.sa.accommodation_service.room.application.ouputports.persistence;

import java.util.Optional;
import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.OutputPort;
import com.sa.accommodation_service.room.domain.Room;

@OutputPort
public interface FindRoomById {
    
    public Optional<Room> findById(UUID id);

}
