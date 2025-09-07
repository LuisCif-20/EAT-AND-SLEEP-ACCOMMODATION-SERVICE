package com.sa.accommodation_service.hotel.application.inputports.gethotelbyid;

import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.InputPort;
import com.sa.accommodation_service.hotel.domain.Hotel;

@InputPort
public interface GetHotelById {
    
    public Hotel getById(UUID id);

}
