package com.sa.accommodation_service.hotel.application.usecases;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.application.outputports.cloud.UploadImage;
import com.sa.accommodation_service.hotel.application.dto.CreateHotelDTO;
import com.sa.accommodation_service.hotel.application.inputports.CreateHotel;
import com.sa.accommodation_service.hotel.application.outputports.persistence.SaveHotel;
import com.sa.accommodation_service.hotel.domain.Hotel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@Component
@UseCase
public class CreateHotelImpl implements CreateHotel {

    private final SaveHotel saveHotel;
    private final UploadImage uploadImage;

    @Override
    @Transactional
    public Hotel create(@Valid CreateHotelDTO createHotelDTO) {
        final String fileName = uploadImage.upload(createHotelDTO.photo());
        final Hotel hotel = createHotelDTO.toDomain(fileName);
        return saveHotel.save(hotel);
    }
    
}
