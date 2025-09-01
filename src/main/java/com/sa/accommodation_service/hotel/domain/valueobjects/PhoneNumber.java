package com.sa.accommodation_service.hotel.domain.valueobjects;

import java.util.regex.Pattern;

import com.sa.accommodation_service.common.domain.annotations.ValueObject;
import com.sa.accommodation_service.common.domain.exceptions.ValueObjectValidationException;

@ValueObject
public record PhoneNumber(String value) {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern
            .compile("^\\d{4}-\\d{4}$");
    
    public PhoneNumber {
        if (value == null || value.isBlank()) {
            throw new ValueObjectValidationException("El numero de telefono no debe estar vacio");
        }
        if (!PHONE_NUMBER_PATTERN.matcher(value).matches()) {
            throw new ValueObjectValidationException("El numero debe tener el siguiente formato: 0000-0000");
        }
    }

}
