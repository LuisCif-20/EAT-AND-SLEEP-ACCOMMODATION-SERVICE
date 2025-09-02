package com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.mapper.HotelPersistenceMapper;
import com.sa.accommodation_service.room.domain.Room;
import com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.entity.RoomEntity;

@Mapper(componentModel = "spring", uses = { HotelPersistenceMapper.class })
public interface RoomPersistenceMapper {

    public Room toDomain(RoomEntity roomEntity);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "roomNumber", source = "roomNumber.value")
    @Mapping(target = "pricePerNight", source = "pricePerNight.value")
    @Mapping(target = "maintenanceCost", source = "maintenanceCost.value")
    public RoomEntity toEntity(Room room);
    
}