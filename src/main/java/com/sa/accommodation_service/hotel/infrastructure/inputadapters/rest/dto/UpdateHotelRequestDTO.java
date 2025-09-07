package com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;
import com.sa.accommodation_service.common.infrastructure.exceptions.FileValidatorException;
import com.sa.accommodation_service.common.infrastructure.utils.file.FileUtils;
import com.sa.accommodation_service.common.infrastructure.utils.file.ImageFileValidator;
import com.sa.accommodation_service.hotel.application.inputports.updatehotel.dto.UpdateHotelDTO;

public record UpdateHotelRequestDTO(

        String name,
        String address,
        String city,
        String phoneNumber,
        MultipartFile photo,
        Boolean active

) {

    public UpdateHotelDTO toUpdateHotelDTO() {
        return new UpdateHotelDTO(name, address, city, phoneNumber, toFileDataDTO(), active);
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
