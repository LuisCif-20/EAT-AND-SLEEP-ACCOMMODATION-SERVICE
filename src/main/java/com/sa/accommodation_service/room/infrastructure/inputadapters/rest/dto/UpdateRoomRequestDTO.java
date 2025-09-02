package com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.room.application.dto.UpdateRoomDTO;

public record UpdateRoomRequestDTO(

    UUID hotelId,
    String roomNumber,
    String description,
    BigDecimal pricePerNight,
    BigDecimal maintenanceCost,
    MultipartFile photo

) {

    public UpdateRoomDTO toUpdateRoomDTO() {
        return new UpdateRoomDTO(
                hotelId, roomNumber, description, pricePerNight, maintenanceCost, photo);
    }
    
}
