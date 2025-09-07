package com.sa.accommodation_service.room.application.inputports.updateroom.dto;

import java.math.BigDecimal;

import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

public record UpdateRoomDTO(

    @Size(min = 1, message = "El numero de habitacion debe superar los {min} caracteres")
    String roomNumber,

    @Size(min = 15, message = "La descripcion debe superar los {min} caracteres")
    String description,

    BigDecimal pricePerNight,
    
    BigDecimal maintenanceCost,

    Boolean active,

    @Valid
    FileDataDTO photo

) { }
