package com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto;

import com.sa.accommodation_service.hotel.application.dto.HotelSearchDTO;

public record HotelSearchRequestDTO(

    Boolean active

) {

    public HotelSearchDTO toHotelSearchDTO() {
        return new HotelSearchDTO(active);
    }

}
