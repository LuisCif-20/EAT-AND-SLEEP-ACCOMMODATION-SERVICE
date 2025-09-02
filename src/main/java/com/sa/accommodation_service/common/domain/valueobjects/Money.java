package com.sa.accommodation_service.common.domain.valueobjects;

import java.math.BigDecimal;

import com.sa.accommodation_service.common.domain.exceptions.ValueObjectValidationException;

public record Money(BigDecimal value) {

    public Money {
        if (value == null) {
            throw new ValueObjectValidationException("El monto no puede ser nulo");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValueObjectValidationException("El monto no puede ser negativo");
        }
    }
    
}

