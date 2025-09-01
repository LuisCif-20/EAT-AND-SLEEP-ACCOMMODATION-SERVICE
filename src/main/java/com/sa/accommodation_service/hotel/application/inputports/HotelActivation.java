package com.sa.accommodation_service.hotel.application.inputports;

import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.InputPort;

@InputPort
public interface HotelActivation {
    
    public void toggle(UUID id);

}
