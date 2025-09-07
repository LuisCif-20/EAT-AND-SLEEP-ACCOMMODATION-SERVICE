package com.sa.accommodation_service.common.application.inputports.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FileDataDTO(

    @NotBlank(message = "El nombre del archivo es obligatorio")
    String fileName,

    @NotBlank(message = "El identificador del archivo es obligatorio")
    String contentType,

    @NotNull(message = "El contenido del archivo es obligatorio")
    byte[] content

) { }
