package com.sa.accommodation_service.room.infrastructure.inputadapters.rest.dto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;
import com.sa.accommodation_service.common.infrastructure.exceptions.FileValidatorException;
import com.sa.accommodation_service.common.infrastructure.utils.file.FileUtils;
import com.sa.accommodation_service.common.infrastructure.utils.file.ImageFileValidator;
import com.sa.accommodation_service.room.application.inputports.updateroom.dto.UpdateRoomDTO;

public record UpdateRoomRequestDTO(

    UUID hotelId,
    String roomNumber,
    String description,
    BigDecimal pricePerNight,
    BigDecimal maintenanceCost,
    MultipartFile photo,
    Boolean active

) {

    public UpdateRoomDTO toUpdateRoomDTO() {
        return new UpdateRoomDTO(
                hotelId, roomNumber, description, pricePerNight, maintenanceCost, toFileDataDTO(), active);
    }

    private FileDataDTO toFileDataDTO() {
        if (photo == null) return null;
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
