package com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sa.accommodation_service.common.infrastructure.annotations.OutputAdapter;
import com.sa.accommodation_service.lodging.application.outputports.persistence.CheckRoomAvailability;
import com.sa.accommodation_service.lodging.application.outputports.persistence.SaveLodging;
import com.sa.accommodation_service.lodging.domain.Lodging;
import com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence.entity.LodgingEntity;
import com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence.entity.LodgingEntityStatus;
import com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence.mapper.LodgingPersistenceMapper;
import com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence.repository.LodgingEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@OutputAdapter
public class LodgingRepository implements SaveLodging, CheckRoomAvailability {

    private LodgingPersistenceMapper lodgingPersistenceMapper;
    private LodgingEntityRepository lodgingEntityRepository;
    
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Lodging save(Lodging lodging) {
        final LodgingEntity lodgingEntity = lodgingEntityRepository
                .save(lodgingPersistenceMapper.toEntity(lodging));
        return lodgingPersistenceMapper.toDomain(lodgingEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkAvailability(UUID roomId, LocalDateTime checkIn, LocalDateTime checkOut) {
        final boolean hasOverlapping = lodgingEntityRepository
                .existsByRoomIdAndStatusNotInAndCheckInBeforeAndCheckOutAfter(
                        roomId,
                        List.of(LodgingEntityStatus.COMPLETED, LodgingEntityStatus.CANCELLED),
                        checkOut,
                        checkIn);
        return !hasOverlapping;
    }

}
