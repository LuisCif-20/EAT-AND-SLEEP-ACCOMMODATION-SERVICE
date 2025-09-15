package com.sa.accommodation_service.lodging.application.outputports.rest;

import java.util.UUID;

import com.sa.accommodation_service.common.application.annotations.OutputPort;

@OutputPort
public interface ExistsCustomerById {
    
    public boolean existsById(UUID id);

}
