package com.sa.accommodation_service.lodging.application.inputports.createlodging;

import com.sa.accommodation_service.common.application.annotations.InputPort;
import com.sa.accommodation_service.lodging.application.inputports.createlodging.dto.CreateLodgingDTO;
import com.sa.accommodation_service.lodging.domain.Lodging;

import jakarta.validation.Valid;

@InputPort
public interface CreateLodging {
    
    public Lodging create(@Valid CreateLodgingDTO createLodgingDTO);

}
