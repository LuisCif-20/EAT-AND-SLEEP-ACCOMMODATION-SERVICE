package com.sa.accommodation_service.lodging.domain.valueobjects;

import java.time.LocalDateTime;

import com.sa.accommodation_service.common.domain.exceptions.ValueObjectValidationException;

public record DateRange(

    LocalDateTime checkIn,
    LocalDateTime checkOut

) {

    public DateRange {
        if (checkIn == null || checkOut == null) {
            throw new ValueObjectValidationException(
                    "La fecha de entrada y/o salida no puede ser nula");
        }
        if (!checkOut.toLocalDate().isAfter(checkIn.toLocalDate())) {
            throw new ValueObjectValidationException(
                    "La estancia minima es de un dia");
        }
    }
    
}
