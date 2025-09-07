package com.sa.accommodation_service.common.infrastructure.inputadapters.rest.handler;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.google.cloud.storage.StorageException;
import com.sa.accommodation_service.common.domain.exceptions.ValueObjectValidationException;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityAlreadyExistsException;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.common.infrastructure.exceptions.FileValidatorException;
import com.sa.accommodation_service.common.infrastructure.inputadapters.rest.utils.ConstraintViolationUtils;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValueObjectValidationException.class)
    public ProblemDetail handleValueObjectValidationException(ValueObjectValidationException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Value Object Validation Error");
        problemDetail.setProperty("error_category", "Validation");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Entity Not Found");
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ProblemDetail handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Entity Already Exists");
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    // @ExceptionHandler(MissingRequestHeaderException.class)
    // public ProblemDetail handleMissingRequestHeaderException(MissingRequestHeaderException e) {
    //     final ProblemDetail problemDetail = ProblemDetail
    //             .forStatusAndDetail(HttpStatus.BAD_REQUEST, "No esta presente el header: " + e.getHeaderName());
    //     problemDetail.setTitle("Missing Required Header");
    //     problemDetail.setProperty("error_category", "Validation");
    //     problemDetail.setProperty("timestamp", Instant.now());
    //     return problemDetail;
    // }

    // @ExceptionHandler(MultipartException.class)
    // public ProblemDetail handleMultipartException(MultipartException e) {
    //     final ProblemDetail problemDetail = ProblemDetail
    //             .forStatusAndDetail(HttpStatus.BAD_REQUEST, "El necesario adjuntar el multipart");
    //     problemDetail.setTitle("Missing Required Multipart");
    //     problemDetail.setProperty("error_category", "Validation");
    //     problemDetail.setProperty("timestamp", Instant.now());
    //     return problemDetail;
    // }

    // @ExceptionHandler(MissingServletRequestPartException.class)
    // public ProblemDetail handleMissingServletRequestPartException(MissingServletRequestPartException e) {
    //     final ProblemDetail problemDetail = ProblemDetail
    //             .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Es necesario el siguiente campo: " + e.getRequestPartName());
    //     problemDetail.setTitle("Missing Required Part");
    //     problemDetail.setProperty("error_category", "Validation");
    //     problemDetail.setProperty("timestamp", Instant.now());
    //     return problemDetail;
    // }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        final ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Es necesario el parametro: " + e.getPropertyName());
        problemDetail.setTitle("Missing Required Propertie");
        problemDetail.setProperty("error_category", "Validation");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    // @ExceptionHandler(HttpMessageNotReadableException.class)
    // public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    //     final ProblemDetail problemDetail = ProblemDetail
    //             .forStatusAndDetail(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud es requerido");
    //     problemDetail.setTitle("Missing Request Body");
    //     problemDetail.setProperty("error_category", "Validation");
    //     problemDetail.setProperty("timestamp", Instant.now());
    //     return problemDetail;
    // }

    // @ExceptionHandler(MissingRequestCookieException.class)
    // public ProblemDetail handleMissingRequestCookieException(MissingRequestCookieException e) {
    //     final ProblemDetail problemDetail = ProblemDetail
    //             .forStatusAndDetail(HttpStatus.BAD_REQUEST, "La siguiente cookie es obligatoria: "
    //                     + e.getCookieName());
    //     problemDetail.setTitle("Missing Request Cookie");
    //     problemDetail.setProperty("error_category", "Validation");
    //     problemDetail.setProperty("timestamp", Instant.now());
    //     return problemDetail;
    // }

    @ExceptionHandler(FileValidatorException.class)
    public ProblemDetail handleFileUploadException(FileValidatorException e) {
        final ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setTitle("File Upload Error");
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(StorageException.class)
    public ProblemDetail handleStorageException(StorageException e) {
        final ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Peticion rechazada");
        problemDetail.setTitle("Google Cloud Storage Error");
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException e) {
        final Map<String, String> errors = ConstraintViolationUtils
                .extractErrors(e.getConstraintViolations());
        final ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Se ha producido uno o mas errores de validacion");
        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("error_category", "Validation");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }
}
