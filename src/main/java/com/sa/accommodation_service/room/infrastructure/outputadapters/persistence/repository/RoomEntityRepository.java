package com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.entity.RoomEntity;

public interface RoomEntityRepository extends JpaRepository<RoomEntity, UUID>, JpaSpecificationExecutor<RoomEntity> {

    public boolean existsByHotelIdAndRoomNumber(UUID hotelId, String roomNumber);

    public boolean existsByHotelIdAndRoomNumberAndIdNot(UUID hotelId, String roomNumber, UUID id);
    
}
