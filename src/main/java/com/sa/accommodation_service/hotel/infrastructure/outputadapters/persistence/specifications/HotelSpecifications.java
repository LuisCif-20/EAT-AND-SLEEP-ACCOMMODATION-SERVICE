package com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.entity.HotelEntity;

public class HotelSpecifications {

    public static Specification<HotelEntity> active(Boolean active) {
        return (root, query, criteriaBuilder) -> active == null ? null
                : criteriaBuilder.equal(root.get("active"), active);
    }
    
}
