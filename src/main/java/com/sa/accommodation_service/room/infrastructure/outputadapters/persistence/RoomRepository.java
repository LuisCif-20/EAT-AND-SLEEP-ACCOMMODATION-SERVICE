package com.sa.accommodation_service.room.infrastructure.outputadapters.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sa.accommodation_service.common.infrastructure.annotations.OutputAdapter;
import com.sa.accommodation_service.room.application.dto.RoomSearchDTO;
import com.sa.accommodation_service.room.application.ouputports.persistence.FindAllRooms;
import com.sa.accommodation_service.room.application.ouputports.persistence.FindRoomById;
import com.sa.accommodation_service.room.application.ouputports.persistence.SaveRoom;
import com.sa.accommodation_service.room.domain.Room;
import com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.entity.RoomEntity;
import com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.mapper.RoomPersistenceMapper;
import com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.repository.RoomEntityRepository;
import com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.specifications.RoomSpecifications;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@OutputAdapter
public class RoomRepository implements SaveRoom, FindRoomById, FindAllRooms {
    
    private final RoomPersistenceMapper roomPersistenceMapper;
    private final RoomEntityRepository roomEntityRepository;
    
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Room save(Room room) {
        final RoomEntity roomEntity = roomEntityRepository
                .save(roomPersistenceMapper.toEntity(room));
        return roomPersistenceMapper.toDomain(roomEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Room> findById(UUID id) {
        return roomEntityRepository.findById(id)
                .map(roomPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> findAll(RoomSearchDTO roomSearchDTO) {
        final Specification<RoomEntity> specification = Specification
                .anyOf(RoomSpecifications.active(roomSearchDTO.active()));
        return roomEntityRepository.findAll(specification)
                .stream()
                .map(roomPersistenceMapper::toDomain)
                .toList();
    }

}
