package com.sa.accommodation_service.room.application.ouputports.persistence;

import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.OutputPort;

@OutputPort
public interface ExistsRoomByHotelIdAndRoomNumber {
    
    public boolean existsByHotelIdAndRoomNumber(UUID hotelId, String roomNumber);

}
