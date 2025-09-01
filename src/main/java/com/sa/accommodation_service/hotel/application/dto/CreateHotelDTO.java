package com.sa.accommodation_service.hotel.application.dto;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.hotel.domain.Hotel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateHotelDTO(

    @NotBlank(message = "El nombre del hotel es obligatorio")
    @Size(min = 5, message = "El nombre debe superar los {min} caracteres")
    String name,

    @NotBlank(message = "La direccion del hotel es obligatoria")
    @Size(min = 5, message = "El direccion debe superar los {min} caracteres")
    String address,

    @NotBlank(message = "La ciudad del hotel es obligatoria")
    @Size(min = 3, message = "La ciudad debe superar los {min} caracteres")
    String city,

    @NotBlank(message = "El numero de telefono del hotel es obligatorio")
    @Size(min = 9, max = 9, message = "El numero de telefono debe tener {min} caracteres")
    String phoneNumber,

    @NotNull(message = "La foto del hotel es obligatoria")
    MultipartFile photo

) {

    public Hotel toDomain(String photo) {
        return new Hotel(name, address, city, phoneNumber, photo);
    }
    
}
