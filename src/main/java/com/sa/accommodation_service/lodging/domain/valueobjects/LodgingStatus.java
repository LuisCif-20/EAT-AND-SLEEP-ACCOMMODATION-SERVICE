package com.sa.accommodation_service.lodging.domain.valueobjects;

import com.sa.accommodation_service.common.domain.exceptions.ValueObjectValidationException;

import lombok.Value;

@Value
public class LodgingStatus {

    public enum State {
        RESERVED,
        ONGOING,
        COMPLETED,
        CANCELLED;
    }

    private final State value;

    public LodgingStatus(String value) {
        if (value == null) {
            throw new ValueObjectValidationException("El estado no puede ser nulo");
        }
        value = value.toUpperCase();
        try {
            this.value = State.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new ValueObjectValidationException(
                    value + " no es un estado valido para el alojamiento");
        }
    }

    public static LodgingStatus createLodgingStatus(String value) {
        final LodgingStatus lodgingStatus = new LodgingStatus(value);
        if (lodgingStatus.value == State.CANCELLED || lodgingStatus.value == State.COMPLETED) {
            throw new ValueObjectValidationException(
                    lodgingStatus.value + " no es valido para un estado inicial del alojamiento");
        }
        return lodgingStatus;
    }

}
