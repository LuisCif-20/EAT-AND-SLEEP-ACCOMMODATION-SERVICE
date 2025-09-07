package com.sa.accommodation_service.hotel.application.usecases;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.application.outputports.cloud.UploadFile;
import com.sa.accommodation_service.hotel.application.inputports.createhotel.CreateHotel;
import com.sa.accommodation_service.hotel.application.inputports.createhotel.dto.CreateHotelDTO;
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
    private final UploadFile uploadFile;

    @Override
    @Transactional
    public Hotel create(@Valid CreateHotelDTO createHotelDTO) {
        uploadFile.upload(createHotelDTO.photo());
        final Hotel hotel = new Hotel(
                createHotelDTO.name(),
                createHotelDTO.address(),
                createHotelDTO.city(),
                createHotelDTO.phoneNumber(),
                createHotelDTO.photo().fileName());
        return saveHotel.save(hotel);
    }
    
}
