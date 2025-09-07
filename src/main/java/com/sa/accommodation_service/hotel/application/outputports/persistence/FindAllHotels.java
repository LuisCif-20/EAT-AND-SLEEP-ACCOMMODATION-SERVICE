package com.sa.accommodation_service.hotel.application.outputports.persistence;

import java.util.List;

import com.sa.accommodation_service.common.application.annotations.OutputPort;
import com.sa.accommodation_service.hotel.application.inputports.getallhotels.dto.HotelSearchDTO;
import com.sa.accommodation_service.hotel.domain.Hotel;

@OutputPort
public interface FindAllHotels {
    
    public List<Hotel> findAll(HotelSearchDTO hotelSearchDTO);

}
