package com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;
import com.sa.accommodation_service.common.infrastructure.exceptions.FileValidatorException;
import com.sa.accommodation_service.common.infrastructure.utils.file.FileUtils;
import com.sa.accommodation_service.common.infrastructure.utils.file.ImageFileValidator;
import com.sa.accommodation_service.room.application.inputports.createroom.dto.CreateRoomDTO;

public record CreateRoomRequestDTO(

    UUID hotelId,
    String roomNumber,
    String description,
    BigDecimal pricePerNight,
    BigDecimal maintenanceCost,
    MultipartFile photo

) {

    public CreateRoomDTO toCreateRoomDTO() {
        return new CreateRoomDTO(
                hotelId, roomNumber, description, pricePerNight, maintenanceCost, toFileDataDTO());
    }

    private FileDataDTO toFileDataDTO() {
        ImageFileValidator.validate(photo);
        final String originalFileName = photo.getOriginalFilename();
        try {
            return new FileDataDTO(
                    FileUtils.generateFileName(originalFileName),
                    photo.getContentType(),
                    photo.getBytes());
        } catch (IOException e) {
            throw new FileValidatorException(
                    "Error al manipular el archivo");
        }
    }
    
}
