package com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.entity.HotelEntity;

public interface HotelEntityRepository extends JpaRepository<HotelEntity, UUID>, JpaSpecificationExecutor<HotelEntity> {
    
}
