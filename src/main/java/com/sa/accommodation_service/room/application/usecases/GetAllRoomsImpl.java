package com.sa.accommodation_service.room.application.usecases;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.room.application.dto.RoomSearchDTO;
import com.sa.accommodation_service.room.application.inputports.GetAllRooms;
import com.sa.accommodation_service.room.application.ouputports.persistence.FindAllRooms;
import com.sa.accommodation_service.room.domain.Room;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@Component
@UseCase
public class GetAllRoomsImpl implements GetAllRooms {

    private final FindAllRooms findAllRooms;
    
    @Override
    @Transactional
    public List<Room> getAll(@Valid RoomSearchDTO roomSearchDTO) {
        return findAllRooms.findAll(roomSearchDTO);
    }

}
