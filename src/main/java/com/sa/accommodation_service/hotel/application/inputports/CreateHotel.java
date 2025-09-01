package com.sa.accommodation_service.hotel.application.inputports;

import com.sa.accommodation_service.common.application.annotations.InputPort;
import com.sa.accommodation_service.hotel.application.dto.CreateHotelDTO;
import com.sa.accommodation_service.hotel.domain.Hotel;

import jakarta.validation.Valid;

@InputPort
public interface CreateHotel {
    
    public Hotel create(@Valid CreateHotelDTO createHotelDTO);

}
