package com.sa.accommodation_service.room.application.usecases;

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
import com.sa.accommodation_service.hotel.application.outputports.persistence.FindHotelById;
import com.sa.accommodation_service.room.application.inputports.updateroom.UpdateRoom;
import com.sa.accommodation_service.room.application.inputports.updateroom.dto.UpdateRoomDTO;
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
    private final FindHotelById findHotelById;
    private final UploadFile uploadImage;
    private final DeleteFile deleteFile;

    @Override
    @Transactional
    public Room update(UUID id, @Valid UpdateRoomDTO updateRoomDTO) {
        final Room room = findRoomById.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una habitacion con el id: " + id));
        if (updateRoomDTO.photo() != null) {
            uploadImage.upload(updateRoomDTO.photo());
            deleteFile.delete(room.getPhoto());
        }
        final Room updatedRoom = updateRoom(updateRoomDTO, room);
        return saveRoom.save(updatedRoom);
    }

    private Room updateRoom(UpdateRoomDTO updateRoomDTO, Room room) {
        return new Room(
            room.getId().value(),
            Optional.ofNullable(updateRoomDTO.hotelId())
                    .map((id) -> findHotelById.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "No existe un hotel con el id: " + id)))
                    .orElse(room.getHotel()),
            updateValueIfNotNull(updateRoomDTO.roomNumber(), room.getRoomNumber().value()),
            updateValueIfNotNull(updateRoomDTO.description(), room.getDescription()),
            updateValueIfNotNull(updateRoomDTO.pricePerNight(), room.getPricePerNight().value()),
            updateValueIfNotNull(updateRoomDTO.maintenanceCost(), room.getMaintenanceCost().value()),
            Optional.ofNullable(updateRoomDTO.photo())
                    .map(FileDataDTO::fileName)
                    .orElse(room.getPhoto()),
            room.isAvailable(),
            updateValueIfNotNull(updateRoomDTO.active(), room.isActive()));
    }

    private <T> T updateValueIfNotNull(T newValue, T currentValue) {
		return newValue == null ? currentValue : newValue;
	}
    

}
