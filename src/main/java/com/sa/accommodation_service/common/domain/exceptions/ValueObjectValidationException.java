package com.sa.accommodation_service.common.domain.exceptions;

public class ValueObjectValidationException extends RuntimeException {
    
    public ValueObjectValidationException(String message) {
        super(message);
    }

}
