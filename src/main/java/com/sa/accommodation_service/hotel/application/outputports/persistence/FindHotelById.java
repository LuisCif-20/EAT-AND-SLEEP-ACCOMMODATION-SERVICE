package com.sa.accommodation_service.hotel.application.outputports.persistence;

import java.util.Optional;
import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.OutputPort;
import com.sa.accommodation_service.hotel.domain.Hotel;

@OutputPort
public interface FindHotelById {
    
    public Optional<Hotel> findById(UUID id);

}
