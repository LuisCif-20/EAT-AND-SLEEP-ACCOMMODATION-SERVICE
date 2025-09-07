package com.sa.accommodation_service.room.application.inputports.createroom.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRoomDTO(

    @NotNull(message = "El id del hotel es obligatorio")
    UUID hotelId,

    @NotBlank(message = "El numero de habitacion es obligatorio")
    @Size(min = 1, message = "El numero de habitacion debe superar los {min} caracteres")
    String roomNumber,

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 15, message = "La descripcion debe superar los {min} caracteres")
    String description,

    @NotNull(message = "El precio por noche es obligatorio")
    BigDecimal pricePerNight,

    @NotNull(message = "El costo de mantenimiento es ogligatorio")
    BigDecimal maintenanceCost,

    @NotNull(message = "El foto de la habitacion es obligatoria")
    @Valid
    FileDataDTO photo

) { }
