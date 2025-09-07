package com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sa.accommodation_service.common.infrastructure.annotations.OutputAdapter;
import com.sa.accommodation_service.hotel.application.inputports.getallhotels.dto.HotelSearchDTO;
import com.sa.accommodation_service.hotel.application.outputports.persistence.FindAllHotels;
import com.sa.accommodation_service.hotel.application.outputports.persistence.FindHotelById;
import com.sa.accommodation_service.hotel.application.outputports.persistence.SaveHotel;
import com.sa.accommodation_service.hotel.domain.Hotel;
import com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.entity.HotelEntity;
import com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.mapper.HotelPersistenceMapper;
import com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.repository.HotelEntityRepository;
import com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.specifications.HotelSpecifications;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@OutputAdapter
public class HotelRepository implements SaveHotel, FindHotelById , FindAllHotels {

    private final HotelEntityRepository hotelEntityRepository;
    private final HotelPersistenceMapper hotelPersistenceMapper;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Hotel save(Hotel hotel) {
        final HotelEntity hotelEntity = hotelEntityRepository
                .save(hotelPersistenceMapper.toEntity(hotel));
        return hotelPersistenceMapper.toDomain(hotelEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Hotel> findById(UUID id) {
        return hotelEntityRepository.findById(id)
                .map(hotelPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Hotel> findAll(HotelSearchDTO hotelSearchDTO) {
        final Specification<HotelEntity> specs = Specification
                .anyOf(HotelSpecifications.active(hotelSearchDTO.active()));
        return hotelEntityRepository.findAll(specs)
                .stream()
                .map(hotelPersistenceMapper::toDomain)
                .toList();
    }

}
