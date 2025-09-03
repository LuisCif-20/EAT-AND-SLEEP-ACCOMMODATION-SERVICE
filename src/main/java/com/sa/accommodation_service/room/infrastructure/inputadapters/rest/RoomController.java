package com.sa.accommodation_service.room.infrastructure.inputadapters.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.accommodation_service.common.infrastructure.annotations.InputAdapter;
import com.sa.accommodation_service.room.application.inputports.CreateRoom;
import com.sa.accommodation_service.room.application.inputports.GetAllRooms;
import com.sa.accommodation_service.room.application.inputports.GetRoomById;
import com.sa.accommodation_service.room.application.inputports.RoomActivation;
import com.sa.accommodation_service.room.application.inputports.UpdateRoom;
import com.sa.accommodation_service.room.domain.Room;
import com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto.CreateRoomRequestDTO;
import com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto.CreateRoomResponseDTO;
import com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto.GetRoomByIdResponseDTO;
import com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto.ShortRoomResponse;
import com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto.RoomSearchRequestDTO;
import com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto.UpdateRoomRequestDTO;
import com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto.UpdateRoomResponseDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RequestMapping("/v1.0/rooms")
@RestController
@InputAdapter
public class RoomController {

    private final CreateRoom createRoom;
    private final UpdateRoom updateRoom;
    private final GetRoomById getRoomById;
    private final GetAllRooms getAllRooms;
    private final RoomActivation roomActivation;

    @PostMapping
    public ResponseEntity<CreateRoomResponseDTO> create(
            @ModelAttribute CreateRoomRequestDTO createRoomRequestDTO) {
        final Room room = createRoom.create(createRoomRequestDTO.toCreateRoomDTO());
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateRoomResponseDTO.fromDomain(room));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateRoomResponseDTO> update(
            @PathVariable UUID id,
            @ModelAttribute UpdateRoomRequestDTO updateRoomRequestDTO) {
        final Room room = updateRoom.update(id, updateRoomRequestDTO.toUpdateRoomDTO());
        return ResponseEntity.status(HttpStatus.OK).body(UpdateRoomResponseDTO.fromDomain(room));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRoomByIdResponseDTO> getById(@PathVariable UUID id) {
        final Room room = getRoomById.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(GetRoomByIdResponseDTO.fromDomain(room));
    }
    
    @GetMapping
    public ResponseEntity<List<ShortRoomResponse>> getAll(RoomSearchRequestDTO roomSearchRequestDTO) {
        final List<ShortRoomResponse> rooms = getAllRooms.getAll(roomSearchRequestDTO.toRoomSearchDTO())
                .stream()
                .map(ShortRoomResponse::fromDomain)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }

    @PatchMapping("/activation/{id}")
    public ResponseEntity<Void> toggle(@PathVariable UUID id) {
        roomActivation.toggle(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
