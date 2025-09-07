package com.sa.accommodation_service.room.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;
import com.sa.accommodation_service.common.application.outputports.cloud.DeleteFile;
import com.sa.accommodation_service.common.application.outputports.cloud.UploadFile;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityAlreadyExistsException;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.room.application.inputports.updateroom.UpdateRoom;
import com.sa.accommodation_service.room.application.inputports.updateroom.dto.UpdateRoomDTO;
import com.sa.accommodation_service.room.application.ouputports.persistence.ExistsRoomByHotelIdAndRoomNumberAndIdNot;
import com.sa.accommodation_service.room.application.ouputports.persistence.FindRoomById;
import com.sa.accommodation_service.room.application.ouputports.persistence.SaveRoom;
import com.sa.accommodation_service.room.domain.Room;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@Component
@UseCase
public class UpdateRoomImpl implements UpdateRoom {
  
    private final FindRoomById findRoomById;
    private final SaveRoom saveRoom;
    private final ExistsRoomByHotelIdAndRoomNumberAndIdNot existsRoomByHotelIdAndRoomNumberAndIdNot;
    private final UploadFile uploadImage;
    private final DeleteFile deleteFile;

    @Override
    @Transactional
    public Room update(UUID id, @Valid UpdateRoomDTO updateRoomDTO) {
        final Room room = findRoomById.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una habitacion con el id: " + id));
        String photo = null;
        try {
            photo = resolvePhoto(updateRoomDTO.photo(), room.getPhoto());
            Room updatedRoom = getUpdatedRoom(updateRoomDTO, photo, room);
            updatedRoom = saveRoom.save(updatedRoom);
            deleteFile.delete(room.getPhoto());
            return updatedRoom;
        } catch (Exception e) {
            if (photo != null && !photo.equals(room.getPhoto())) {
                deleteFile.delete(photo);
            }
            throw e;
        }
    }

    private Room getUpdatedRoom(UpdateRoomDTO updateRoomDTO, String photo, Room room) {
        return new Room(
                room.getId().value(),
                room.getHotel(),
                resolveRoomNumber(
                        updateRoomDTO.roomNumber(), room),
                resolveValue(updateRoomDTO.description(), room.getDescription()),
                resolveValue(updateRoomDTO.pricePerNight(), room.getPricePerNight().value()),
                resolveValue(updateRoomDTO.maintenanceCost(), room.getMaintenanceCost().value()),
                room.isAvailable(),
                resolveValue(updateRoomDTO.active(), room.isActive()), 
                photo);
    }

    private String resolveRoomNumber(String newRoomNumber, Room room) {
        if (newRoomNumber == null) return room.getRoomNumber().value();
        final boolean exists = existsRoomByHotelIdAndRoomNumberAndIdNot
                .existsByHotelIdAndRoomNumberAndIdNot(
                        room.getHotel().getId().value(),
                        newRoomNumber,
                        room.getId().value());
        if (exists) {
            throw new EntityAlreadyExistsException(
                    "Ya existe una habitacion con el numero: " + newRoomNumber
                            + " en el hotel con id: " + room.getHotel().getId().value());
        }
        return newRoomNumber;
    }

    private String resolvePhoto(FileDataDTO newPhoto, String currentPhoto) {
        if (newPhoto == null) return currentPhoto;
        uploadImage.upload(newPhoto);
        return newPhoto.fileName();
    }

    private <T> T resolveValue(T newValue, T currentValue) {
		return newValue == null || newValue.equals(currentValue) ? currentValue : newValue;
	}
    
}
