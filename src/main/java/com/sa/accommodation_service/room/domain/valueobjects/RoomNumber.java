package com.sa.accommodation_service.room.domain.valueobjects;

import java.util.regex.Pattern;

import com.sa.accommodation_service.common.domain.exceptions.ValueObjectValidationException;

public record RoomNumber(String value) {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern
            .compile("^(?=.{1,10}$)\\d.*[A-Z]?$");
    
    public RoomNumber {
        if (value == null) {
            throw new ValueObjectValidationException("El numero de la habitacion no puede ser nulo");
        }
        final String roomNumberNormalized = value.toUpperCase();
        if (!PHONE_NUMBER_PATTERN.matcher(roomNumberNormalized).matches()) {
            throw new ValueObjectValidationException(
                    "El numero de habitacion debe tener uno o mas digitos y puede terminar con una letra mayuscula");
        }
        value = roomNumberNormalized;
    }

}
