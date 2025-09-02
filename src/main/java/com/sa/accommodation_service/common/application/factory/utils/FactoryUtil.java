package com.sa.accommodation_service.common.application.factory.utils;

public class FactoryUtil {
    
    public static <T> T updateValueIfNotNull(T newValue, T currentValue) {
        return newValue == null ? currentValue : newValue;
    }

}
