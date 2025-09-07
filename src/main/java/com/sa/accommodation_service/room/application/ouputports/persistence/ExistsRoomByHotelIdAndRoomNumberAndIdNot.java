package com.sa.accommodation_service.room.application.ouputports.persistence;

import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.OutputPort;

@OutputPort
public interface ExistsRoomByHotelIdAndRoomNumberAndIdNot {
    
    public boolean existsByHotelIdAndRoomNumberAndIdNot(UUID hotelId, String roomNumber, UUID id);

}
