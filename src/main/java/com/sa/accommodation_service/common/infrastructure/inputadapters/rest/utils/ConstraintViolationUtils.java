package com.sa.accommodation_service.common.infrastructure.inputadapters.rest.utils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;

public class ConstraintViolationUtils {

    private ConstraintViolationUtils() {
        
    }
    
    public static Map<String, String> extractErrors(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .collect(Collectors.toMap(
                        violation -> getFieldName(violation.getPropertyPath().toString()),
                        ConstraintViolation::getMessage,
                        (existing, newValue) -> existing));
    }

    private static String getFieldName(String propertyPath) {
        final String[] parts = propertyPath.split("\\.");
        return parts[parts.length - 1];
    }

}
