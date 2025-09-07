package com.sa.accommodation_service.common.infrastructure.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {
    
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

}
