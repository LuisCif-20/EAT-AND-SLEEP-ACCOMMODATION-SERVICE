package com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.accommodation_service.lodging.domain.Lodging;
import com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence.entity.LodgingEntity;
import com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.mapper.RoomPersistenceMapper;

@Mapper(componentModel = "spring", uses = { RoomPersistenceMapper.class })
public interface LodgingPersistenceMapper {

    
    public Lodging toDomain(LodgingEntity lodgingEntity);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "customerId", source = "customerId.value")
    @Mapping(target = "totalPrice", source = "totalPrice.value")
    @Mapping(target = "checkIn", source = "stayPeriod.checkIn")
    @Mapping(target = "checkOut", source = "stayPeriod.checkOut")
    @Mapping(target = "status", source = "status.value")
    public LodgingEntity toEntity(Lodging lodging);
    
}
