package com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.accommodation_service.hotel.domain.Hotel;
import com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.entity.HotelEntity;

@Mapper(componentModel = "spring")
public interface HotelPersistenceMapper {

    public Hotel toDomain(HotelEntity hotelEntity);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "phoneNumber", source = "phoneNumber.value")
    public HotelEntity toEntity(Hotel hotel);
    
}