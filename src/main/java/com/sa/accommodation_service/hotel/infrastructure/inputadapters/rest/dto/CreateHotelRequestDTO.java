package com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.hotel.application.dto.CreateHotelDTO;

public record CreateHotelRequestDTO(

    String name,
    String address,
    String city,
    String phoneNumber,
    MultipartFile photo

) {

    public CreateHotelDTO toCreateHotelDTO() {
        return new CreateHotelDTO(name, address, city, phoneNumber, photo);
    }
    
}
