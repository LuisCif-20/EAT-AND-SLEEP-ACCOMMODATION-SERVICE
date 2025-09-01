package com.sa.accommodation_service.hotel.application.outputports.persistence;

import com.sa.accommodation_service.common.application.annotations.OutputPort;
import com.sa.accommodation_service.hotel.domain.Hotel;

@OutputPort
public interface SaveHotel {
    
    public Hotel save(Hotel hotel);

}
