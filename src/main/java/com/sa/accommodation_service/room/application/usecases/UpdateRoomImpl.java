package com.sa.accommodation_service.room.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.application.outputports.cloud.DeleteFile;
import com.sa.accommodation_service.common.application.outputports.cloud.UploadImage;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.hotel.application.outputports.persistence.FindHotelById;
import com.sa.accommodation_service.hotel.domain.Hotel;
import com.sa.accommodation_service.room.application.dto.UpdateRoomDTO;
import com.sa.accommodation_service.room.application.facotry.RoomFactory;
import com.sa.accommodation_service.room.application.inputports.UpdateRoom;
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
    private final UploadImage uploadImage;
    private final DeleteFile deleteFile;
    private final RoomFactory roomFactory;

    @Override
    @Transactional
    public Room update(UUID id, @Valid UpdateRoomDTO updateRoomDTO) {
        final Room room = findRoomById.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una habitacion con el id: " + id));
        Hotel hotel = null;
        if (updateRoomDTO.hotelId() != null) {
            hotel = findHotelById.findById(updateRoomDTO.hotelId())
                    .orElseThrow(() -> new EntityNotFoundException("No existe un hotel con el id: "
                            + updateRoomDTO.hotelId()));
        }
        String fileName = null;
        if (updateRoomDTO.photo() != null) {
            fileName = uploadImage.upload(updateRoomDTO.photo());
        }
        if (fileName != null) {
            deleteFile.delete(room.getPhoto());
        }
        return saveRoom
                .save(roomFactory.updateFromDTO(updateRoomDTO, hotel, fileName, room));
    }

}
