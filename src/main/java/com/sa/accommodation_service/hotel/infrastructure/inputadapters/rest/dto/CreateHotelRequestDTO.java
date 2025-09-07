package com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;
import com.sa.accommodation_service.common.infrastructure.exceptions.FileValidatorException;
import com.sa.accommodation_service.common.infrastructure.utils.file.FileUtils;
import com.sa.accommodation_service.common.infrastructure.utils.file.ImageFileValidator;
import com.sa.accommodation_service.hotel.application.inputports.createhotel.dto.CreateHotelDTO;

public record CreateHotelRequestDTO(

    String name,
    String address,
    String city,
    String phoneNumber,
    MultipartFile photo

) {

    public CreateHotelDTO toCreateHotelDTO() {
        return new CreateHotelDTO(name, address, city, phoneNumber, toFileDataDTO());
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
