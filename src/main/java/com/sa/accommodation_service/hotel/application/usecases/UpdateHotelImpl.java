package com.sa.accommodation_service.hotel.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.application.outputports.cloud.UploadImage;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.hotel.application.dto.UpdateHotelDTO;
import com.sa.accommodation_service.hotel.application.factory.HotelFactory;
import com.sa.accommodation_service.hotel.application.inputports.UpdateHotel;
import com.sa.accommodation_service.hotel.application.outputports.persistence.FindHotelById;
import com.sa.accommodation_service.hotel.application.outputports.persistence.SaveHotel;
import com.sa.accommodation_service.hotel.domain.Hotel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@Component
@UseCase
public class UpdateHotelImpl implements UpdateHotel {
    
    private final FindHotelById findHotelById;
    private final HotelFactory hotelFactory;
    private final SaveHotel saveHotel;
    private final UploadImage uploadImage;

    @Override
    @Transactional
    public Hotel update(UUID id, @Valid UpdateHotelDTO updateHotelDTO) {
        final Hotel hotel = findHotelById.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe un hotel con el id: " + id));
        String fileName = null;
        if (updateHotelDTO.photo() != null) {
            fileName = uploadImage.upload(updateHotelDTO.photo());
        }
        return saveHotel.save(hotelFactory.updateFromDTO(updateHotelDTO, fileName, hotel));
    }
    
}
