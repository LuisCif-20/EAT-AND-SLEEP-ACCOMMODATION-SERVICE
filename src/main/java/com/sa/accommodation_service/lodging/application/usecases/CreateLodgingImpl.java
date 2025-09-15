package com.sa.accommodation_service.lodging.application.usecases;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityAlreadyExistsException;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.lodging.application.inputports.createlodging.CreateLodging;
import com.sa.accommodation_service.lodging.application.inputports.createlodging.dto.CreateLodgingDTO;
import com.sa.accommodation_service.lodging.application.outputports.persistence.CheckRoomAvailability;
import com.sa.accommodation_service.lodging.application.outputports.persistence.SaveLodging;
import com.sa.accommodation_service.lodging.application.outputports.rest.ExistsCustomerById;
import com.sa.accommodation_service.lodging.domain.Lodging;
import com.sa.accommodation_service.room.application.ouputports.persistence.FindRoomById;
import com.sa.accommodation_service.room.domain.Room;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@Component
@UseCase
public class CreateLodgingImpl implements CreateLodging {

    private final FindRoomById findRoomById;
    private final ExistsCustomerById existsCustomerById;
    private final CheckRoomAvailability checkRoomAvailability;
    private final SaveLodging saveLodging;

    @Override
    public Lodging create(@Valid CreateLodgingDTO createLodgingDTO) {
        final Room room = findRoomById.findById(createLodgingDTO.roomId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una habitacion con el id: " + createLodgingDTO.roomId()));
        if (!existsCustomerById.existsById(createLodgingDTO.customerId())) {
            throw new EntityNotFoundException(
                    "No existe un cliente con el id: " + createLodgingDTO.customerId());
        }
        checkAvailability(createLodgingDTO.roomId(), createLodgingDTO.checkIn(), createLodgingDTO.checkOut());
        final Lodging lodging = new Lodging(
                room,
                createLodgingDTO.customerId(),
                createLodgingDTO.checkIn(),
                createLodgingDTO.checkOut(),
                createLodgingDTO.status());
        return saveLodging.save(lodging);
    }

    private void checkAvailability(UUID id, LocalDateTime checkIn, LocalDateTime checkOut) {
        final boolean isAvailable = checkRoomAvailability
                .checkAvailability(id, checkIn, checkOut);
        if (!isAvailable) {
            throw new EntityAlreadyExistsException(
                    "Ya existe un alojamiento reservado o activo en el periodo "
                            + checkIn + " - " + checkOut);
        }
    }

}
