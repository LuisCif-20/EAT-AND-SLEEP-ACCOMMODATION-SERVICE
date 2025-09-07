package com.sa.accommodation_service.common.domain.valueobjects;

import java.util.UUID;

import com.sa.accommodation_service.common.domain.annotations.ValueObject;
import com.sa.accommodation_service.common.domain.exceptions.ValueObjectValidationException;

@ValueObject
public record Id(UUID value) {

    public Id {
        if (value == null) {
            throw new ValueObjectValidationException("El id no puede ser nulo");
        }
    }

    public static Id generate() {
        return new Id(UUID.randomUUID());
    }
    
}
