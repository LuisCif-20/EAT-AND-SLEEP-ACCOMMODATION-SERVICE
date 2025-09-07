package com.sa.accommodation_service.common.infrastructure.utils.file;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.common.infrastructure.exceptions.FileValidatorException;

public class ImageFileValidator {
    
    private static final Set<String> ALLOWED_MIME_TYPES = Set.of("image/jpeg", "image/png");
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png");
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    private ImageFileValidator() {
        
    }

    public static void validate(MultipartFile file) {
        validateBasic(file);
        validateExtension(file);
        validateMimeType(file);
        validateImageIntegrity(file);
    }

    private static void validateBasic(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileValidatorException("El archivo no puede ser nulo");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileValidatorException("El tamaño del archivo excede el límite permitido");
        }
    }

    private static void validateExtension(MultipartFile file) {
        final String extension = FileUtils.extractExtension(file.getOriginalFilename());
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new FileValidatorException("Extensión no permitida: " + extension +
                    ". Extensiones permitidas: " + String.join(", ", ALLOWED_EXTENSIONS));
        }
    }

    private static void validateMimeType(MultipartFile file) {
        final String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_MIME_TYPES.contains(contentType)) {
            throw new FileValidatorException("Tipo de archivo inválido: " + contentType +
                    ". Tipos permitidos: " + String.join(", ", ALLOWED_MIME_TYPES));
        }
    }

    private static void validateImageIntegrity(MultipartFile file) {
        try {
            final BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new FileValidatorException("El archivo no es una imagen válida o está corrupto");
            }
        } catch (IOException e) {
            throw new FileValidatorException("Error al validar la imagen");
        }
    }

}
