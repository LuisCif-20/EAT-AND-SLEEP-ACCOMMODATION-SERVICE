package com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.room.application.dto.CreateRoomDTO;

public record CreateRoomRequestDTO(

    UUID hotelId,
    String roomNumber,
    String description,
    BigDecimal pricePerNight,
    BigDecimal maintenanceCost,
    MultipartFile photo

) {

    public CreateRoomDTO toCreateRoomDTO() {
        return new CreateRoomDTO(
                hotelId, roomNumber, description, pricePerNight, maintenanceCost, photo);
    }
    
}
