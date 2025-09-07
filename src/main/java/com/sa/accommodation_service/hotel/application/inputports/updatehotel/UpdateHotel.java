package com.sa.accommodation_service.hotel.application.inputports.updatehotel;

import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.InputPort;
import com.sa.accommodation_service.hotel.application.inputports.updatehotel.dto.UpdateHotelDTO;
import com.sa.accommodation_service.hotel.domain.Hotel;

import jakarta.validation.Valid;

@InputPort
public interface UpdateHotel {

    public Hotel update(UUID id, @Valid UpdateHotelDTO updateHotelDTO);
    
}
