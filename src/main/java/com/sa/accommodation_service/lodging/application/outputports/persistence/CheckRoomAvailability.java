package com.sa.accommodation_service.lodging.application.outputports.persistence;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.OutputPort;

@OutputPort
public interface CheckRoomAvailability {
    
    public boolean checkAvailability(UUID roomId, LocalDateTime checkIn, LocalDateTime checkOut);

}
