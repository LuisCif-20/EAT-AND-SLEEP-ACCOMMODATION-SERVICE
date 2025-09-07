package com.sa.accommodation_service.room.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.application.outputports.cloud.DeleteFile;
import com.sa.accommodation_service.common.application.outputports.cloud.UploadFile;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityAlreadyExistsException;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.hotel.application.outputports.persistence.FindHotelById;
import com.sa.accommodation_service.hotel.domain.Hotel;
import com.sa.accommodation_service.room.application.inputports.createroom.CreateRoom;
import com.sa.accommodation_service.room.application.inputports.createroom.dto.CreateRoomDTO;
import com.sa.accommodation_service.room.application.ouputports.persistence.ExistsRoomByHotelIdAndRoomNumber;
import com.sa.accommodation_service.room.application.ouputports.persistence.SaveRoom;
import com.sa.accommodation_service.room.domain.Room;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@Component
@UseCase
public class CreateRoomImpl implements CreateRoom {

    private final FindHotelById findHotelById;
    private final ExistsRoomByHotelIdAndRoomNumber existsRoomByHotelIdAndRoomNumber;
    private final SaveRoom saveRoom;
    private final UploadFile uploadImage;
    private final DeleteFile deleteFile;

    @Override
    @Transactional
    public Room create(@Valid CreateRoomDTO createRoomDTO) {
        final Hotel hotel = findHotelById.findById(createRoomDTO.hotelId())
                .orElseThrow(() -> new EntityNotFoundException("No existe un hotel con el id: "
                        + createRoomDTO.hotelId()));
        ensureUniqueRoomNumberForHotel(hotel.getId().value(), createRoomDTO.roomNumber());
        uploadImage.upload(createRoomDTO.photo());
        try {
            final Room room = new Room(
                    hotel,
                    createRoomDTO.roomNumber(),
                    createRoomDTO.description(),
                    createRoomDTO.pricePerNight(),
                    createRoomDTO.maintenanceCost(),
                    createRoomDTO.photo().fileName());
            return saveRoom.save(room);
        } catch (RuntimeException e) {
            deleteFile.delete(createRoomDTO.photo().fileName());
            throw e;
        }
    }

    private void ensureUniqueRoomNumberForHotel(UUID hotelId, String roomNumber) {
        if (existsRoomByHotelIdAndRoomNumber.existsByHotelIdAndRoomNumber(hotelId, roomNumber)) {
            throw new EntityAlreadyExistsException(
                    "Ya existe una habitacion con el numero: " + roomNumber
                            + " en el hotel con id: " + hotelId);
        }
    }

}
