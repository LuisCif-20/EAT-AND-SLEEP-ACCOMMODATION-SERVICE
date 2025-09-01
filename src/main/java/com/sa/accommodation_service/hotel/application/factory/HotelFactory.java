package com.sa.accommodation_service.hotel.application.factory;

import org.springframework.stereotype.Component;

import com.sa.accommodation_service.hotel.application.dto.UpdateHotelDTO;
import com.sa.accommodation_service.hotel.domain.Hotel;

@Component
public class HotelFactory {
    
    public Hotel updateFromDTO(UpdateHotelDTO updateHotelDTO, String photo, Hotel hotel) {
        return new Hotel(
                hotel.getId().value(),
                updateValueIfNotNull(updateHotelDTO.name(), hotel.getName()),
                updateValueIfNotNull(updateHotelDTO.address(), hotel.getAddress()),
                updateValueIfNotNull(updateHotelDTO.city(), hotel.getCity()),
                updateValueIfNotNull(updateHotelDTO.phoneNumber(), hotel.getPhoneNumber().value()),
                updateValueIfNotNull(photo, hotel.getPhoto()),
                hotel.isActive());
    }

    private String updateValueIfNotNull(String newValue, String currentValue) {
        return newValue != null ? newValue : currentValue;
    }

}
