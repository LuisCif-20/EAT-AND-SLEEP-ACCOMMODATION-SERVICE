package com.sa.accommodation_service.room.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Size;

public record UpdateRoomDTO(

    UUID hotelId,

    @Size(min = 1, message = "El numero de habitacion debe superar los {min} caracteres")
    String roomNumber,

    @Size(min = 15, message = "La descripcion debe superar los {min} caracteres")
    String description,

    BigDecimal pricePerNight,
    
    BigDecimal maintenanceCost,
    
    MultipartFile photo

) { }
