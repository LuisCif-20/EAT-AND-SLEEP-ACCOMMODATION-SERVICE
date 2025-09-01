package com.sa.accommodation_service.common.infrastructure.outputadapters.cloud.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.common.infrastructure.exceptions.FileUploadException;

public class ImageFileValidator {

    private static final Set<String> ALLOWED_MIME_TYPES = Set.of("image/jpeg", "image/png");
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png");
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    private ImageFileValidator() {
    }

    public static String generateValidImageFileName(MultipartFile multipartFile) {
        validateFile(multipartFile);
        final String originalFileName = multipartFile.getOriginalFilename();
        final String extension = extractAndValidateExtension(originalFileName);
        validateImageContent(multipartFile);
        return generateUniqueFileName(extension);
    }

    private static void validateFile(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new FileUploadException("El archivo no puede estar vacío");
        }
        if (multipartFile.getSize() > MAX_FILE_SIZE) {
            throw new FileUploadException("El tamaño del archivo excede el límite permitido");
        }
    }

    private static String extractAndValidateExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            throw new FileUploadException("El archivo no cuenta con una extensión válida");
        }
        final String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new FileUploadException("Extensión de archivo no permitida: " + extension +
                    ". Extensiones permitidas: " + String.join(", ", ALLOWED_EXTENSIONS));
        }
        return extension;
    }

    private static void validateImageContent(MultipartFile multipartFile) {
        validateMimeType(multipartFile.getContentType());
        validateImageIntegrity(multipartFile);
    }

    private static void validateMimeType(String contentType) {
        if (contentType == null || !ALLOWED_MIME_TYPES.contains(contentType)) {
            throw new FileUploadException("Tipo de archivo inválido: " + contentType +
                    ". Tipos permitidos: " + String.join(", ", ALLOWED_MIME_TYPES));
        }
    }

    private static void validateImageIntegrity(MultipartFile multipartFile) {
        try {
            final BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (image == null) {
                throw new FileUploadException("El archivo no es una imagen válida o está corrupto");
            }
        } catch (IOException e) {
            throw new FileUploadException("Error al validar la imagen");
        }
    }

    private static String generateUniqueFileName(String extension) {
        return UUID.randomUUID() + "." + extension;
    }

}
