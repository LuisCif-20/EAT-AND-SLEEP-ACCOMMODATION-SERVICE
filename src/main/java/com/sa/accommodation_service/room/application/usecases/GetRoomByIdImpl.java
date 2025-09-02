package com.sa.accommodation_service.room.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.room.application.inputports.GetRoomById;
import com.sa.accommodation_service.room.application.ouputports.persistence.FindRoomById;
import com.sa.accommodation_service.room.domain.Room;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@UseCase
public class GetRoomByIdImpl implements GetRoomById {
    
    private final FindRoomById findRoomById;

    @Override
    @Transactional
    public Room getById(UUID id) {
        final Room room = findRoomById.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una habitacion con el id: " + id));
        return room;
    }

}
