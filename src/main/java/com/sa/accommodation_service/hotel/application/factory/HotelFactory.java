package com.sa.accommodation_service.hotel.application.factory;

import org.springframework.stereotype.Component;

import com.sa.accommodation_service.common.application.factory.utils.FactoryUtil;
import com.sa.accommodation_service.hotel.application.dto.UpdateHotelDTO;
import com.sa.accommodation_service.hotel.domain.Hotel;

@Component
public class HotelFactory {
    
    public Hotel updateFromDTO(UpdateHotelDTO updateHotelDTO, String photo, Hotel hotel) {
        return new Hotel(
                hotel.getId().value(),
                FactoryUtil
                        .updateValueIfNotNull(updateHotelDTO.name(), hotel.getName()),
                FactoryUtil
                        .updateValueIfNotNull(updateHotelDTO.address(), hotel.getAddress()),
                FactoryUtil
                        .updateValueIfNotNull(updateHotelDTO.city(), hotel.getCity()),
                FactoryUtil
                        .updateValueIfNotNull(updateHotelDTO.phoneNumber(), hotel.getPhoneNumber().value()),
                FactoryUtil
                        .updateValueIfNotNull(photo, hotel.getPhoto()),
                hotel.isActive());
    }

}
