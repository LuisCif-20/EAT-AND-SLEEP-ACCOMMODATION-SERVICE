package com.sa.accommodation_service.hotel.application.usecases;

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
        String photo = null;
        try {
            photo = resolvePhoto(updateHotelDTO.photo(), hotel.getPhoto());
            Hotel updatedHotel = getUpdatedHotel(updateHotelDTO, photo, hotel);
            updatedHotel = saveHotel.save(updatedHotel);
            deleteFile.delete(hotel.getPhoto());
            return updatedHotel;
        } catch (RuntimeException e) {
            if (photo != null && !photo.equals(hotel.getPhoto())) {
                deleteFile.delete(photo);
            }
            throw e;
        }
    }

    private Hotel getUpdatedHotel(UpdateHotelDTO updateHotelDTO, String photo, Hotel hotel) {
        return new Hotel(
                hotel.getId().value(),
                resolveValue(updateHotelDTO.name(), hotel.getName()),
                resolveValue(updateHotelDTO.address(), hotel.getAddress()),
                resolveValue(updateHotelDTO.city(), hotel.getCity()),
                resolveValue(updateHotelDTO.phoneNumber(), hotel.getPhoneNumber().value()),
                resolveValue(updateHotelDTO.active(), hotel.isActive()),
                photo);
    }

    private String resolvePhoto(FileDataDTO newPhoto, String currentPhoto) {
        if (newPhoto == null) return currentPhoto;
        uploadImage.upload(newPhoto);
        return newPhoto.fileName();
    }

    private <T> T resolveValue(T newValue, T currentValue) {
		return newValue == null ? currentValue : newValue;
	}
    
}
