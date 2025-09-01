package com.sa.accommodation_service.hotel.application.inputports;

import java.util.List;

import com.sa.accommodation_service.common.application.annotations.InputPort;
import com.sa.accommodation_service.hotel.application.dto.HotelSearchDTO;
import com.sa.accommodation_service.hotel.domain.Hotel;

import jakarta.validation.Valid;

@InputPort
public interface GetAllHotels {

    public List<Hotel> getAll(@Valid HotelSearchDTO hotelSearchDTO);
    
}
