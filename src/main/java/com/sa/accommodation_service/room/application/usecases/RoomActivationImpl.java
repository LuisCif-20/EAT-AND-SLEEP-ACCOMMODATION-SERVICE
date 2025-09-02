package com.sa.accommodation_service.room.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.room.application.inputports.RoomActivation;
import com.sa.accommodation_service.room.application.ouputports.persistence.FindRoomById;
import com.sa.accommodation_service.room.application.ouputports.persistence.SaveRoom;
import com.sa.accommodation_service.room.domain.Room;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@UseCase
public class RoomActivationImpl implements RoomActivation {
    
    private final FindRoomById findRoomById;
    private final SaveRoom saveRoom;

    @Override
    @Transactional
    public void toggle(UUID id) {
        final Room room = findRoomById.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una habitacion con el id: " + id));
        room.toggleActive();
        saveRoom.save(room);
    }

}
