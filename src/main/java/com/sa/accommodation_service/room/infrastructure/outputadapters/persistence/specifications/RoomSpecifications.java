package com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.entity.RoomEntity;

public class RoomSpecifications {

    public static Specification<RoomEntity> active(Boolean active) {
        return (root, query, criteriaBuilder) -> active == null ? null
                : criteriaBuilder.equal(root.get("active"), active);
    }
    
}
