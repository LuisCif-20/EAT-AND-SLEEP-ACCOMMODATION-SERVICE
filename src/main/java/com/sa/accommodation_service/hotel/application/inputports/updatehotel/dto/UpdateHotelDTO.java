package com.sa.accommodation_service.hotel.application.inputports.updatehotel.dto;

import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

public record UpdateHotelDTO(

    @Size(min = 5, message = "El nombre debe superar los {min} caracteres")
    String name,

    @Size(min = 5, message = "El direccion debe superar los {min} caracteres")
    String address,

    @Size(min = 3, message = "La ciudad debe superar los {min} caracteres")
    String city,

    @Size(min = 9, max = 9, message = "El numero de telefono debe tener {min} caracteres")
    String phoneNumber,

    @Valid
    FileDataDTO photo,

    Boolean active

) { }
