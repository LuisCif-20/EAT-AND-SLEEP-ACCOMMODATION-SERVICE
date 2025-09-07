package com.sa.accommodation_service.common.infrastructure.utils.file;

import java.util.UUID;

import com.sa.accommodation_service.common.infrastructure.exceptions.FileValidatorException;

public class FileUtils {
    
    private FileUtils() {

    }

    public static String generateFileName(String originalFileName) {
        final String extension = extractExtension(originalFileName);
        return UUID.randomUUID() + "." + extension;
    }

    public static String extractExtension(String originalFileName) {
        if (originalFileName == null || !originalFileName.contains(".")) {
            throw new FileValidatorException("El archivo no tiene extensión válida");
        }
        return originalFileName.substring(originalFileName.lastIndexOf('.') + 1).toLowerCase();
    }
    
}
