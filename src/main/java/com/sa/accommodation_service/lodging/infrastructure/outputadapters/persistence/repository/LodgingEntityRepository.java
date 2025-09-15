package com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence.entity.LodgingEntity;
import com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence.entity.LodgingEntityStatus;

public interface LodgingEntityRepository extends JpaRepository<LodgingEntity, UUID>, JpaSpecificationExecutor<LodgingEntity> {
    
    public boolean existsByRoomIdAndStatusNotInAndCheckInBeforeAndCheckOutAfter(
            UUID roomId, 
            Collection<LodgingEntityStatus> excludedStatuses,
            LocalDateTime checkOut,
            LocalDateTime checkIn);

}
