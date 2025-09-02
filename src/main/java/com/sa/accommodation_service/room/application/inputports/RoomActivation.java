package com.sa.accommodation_service.room.application.inputports;

import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.InputPort;

@InputPort
public interface RoomActivation {
    
    public void toggle(UUID id);

}
