package com.sa.accommodation_service.lodging.application.inputports.createlodging.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateLodgingDTO(

    @NotNull(message = "El id de la habitacion es obligatorio")
    UUID roomId,

    @NotNull(message = "El id del cliente es obligatorio")
    UUID customerId,

    @NotNull(message = "El nit es obligatorio")
    String nit,

    @NotNull(message = "La fecha inicial del alojamiento es obligatoria")
    @FutureOrPresent(message = "La fecha inicial del alojamiento no puede ser pasada")
    LocalDateTime checkIn,

    @NotNull(message = "La fecha final del alojamiento es obligatoria")
    @FutureOrPresent(message = "La fecha final del alojamiento no puede ser pasada")
    LocalDateTime checkOut,

    @NotBlank(message = "El estado del alojamiento es obligatorio")
    String status

) { }
