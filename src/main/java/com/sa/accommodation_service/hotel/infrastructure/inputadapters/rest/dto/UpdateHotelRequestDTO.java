package com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.hotel.application.dto.UpdateHotelDTO;

public record UpdateHotelRequestDTO(

    String name,
    String address,
    String city,
    String phoneNumber,
    MultipartFile photo

) {
    
    public UpdateHotelDTO toUpdateHotelDTO() {
        return new UpdateHotelDTO(name, address, city, phoneNumber, photo);
    }

}
