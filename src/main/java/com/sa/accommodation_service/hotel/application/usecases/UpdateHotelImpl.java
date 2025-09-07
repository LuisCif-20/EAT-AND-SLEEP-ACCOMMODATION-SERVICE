package com.sa.accommodation_service.hotel.application.usecases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;
import com.sa.accommodation_service.common.application.outputports.cloud.DeleteFile;
import com.sa.accommodation_service.common.application.outputports.cloud.UploadFile;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.hotel.application.inputports.updatehotel.UpdateHotel;
import com.sa.accommodation_service.hotel.application.inputports.updatehotel.dto.UpdateHotelDTO;
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
    private final SaveHotel saveHotel;
    private final UploadFile uploadImage;
    private final DeleteFile deleteFile;

    @Override
    @Transactional
    public Hotel update(UUID id, @Valid UpdateHotelDTO updateHotelDTO) {
        final Hotel hotel = findHotelById.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe un hotel con el id: " + id));
        if (updateHotelDTO.photo() != null) {
            uploadImage.upload(updateHotelDTO.photo());
            deleteFile.delete(hotel.getPhoto());
        }
        final Hotel updatedHotel = updateHotel(updateHotelDTO, hotel);
        return saveHotel.save(updatedHotel);
    }

    private Hotel updateHotel(UpdateHotelDTO updateHotelDTO, Hotel hotel) {
        return new Hotel(
                hotel.getId().value(),
                updateValueIfNotNull(updateHotelDTO.name(), hotel.getName()),
                updateValueIfNotNull(updateHotelDTO.address(), hotel.getAddress()),
                updateValueIfNotNull(updateHotelDTO.city(), hotel.getCity()),
                updateValueIfNotNull(updateHotelDTO.phoneNumber(), hotel.getPhoneNumber().value()),
                Optional.ofNullable(updateHotelDTO.photo())
                        .map(FileDataDTO::fileName)
                        .orElse(hotel.getPhoto()),
                updateValueIfNotNull(updateHotelDTO.active(), hotel.isActive()));
    }

    private <T> T updateValueIfNotNull(T newValue, T currentValue) {
		return newValue == null ? currentValue : newValue;
	}
    
}
