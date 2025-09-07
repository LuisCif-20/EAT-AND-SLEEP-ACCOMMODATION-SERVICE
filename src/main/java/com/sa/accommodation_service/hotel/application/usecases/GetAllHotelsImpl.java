package com.sa.accommodation_service.hotel.application.usecases;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.hotel.application.inputports.getallhotels.GetAllHotels;
import com.sa.accommodation_service.hotel.application.inputports.getallhotels.dto.HotelSearchDTO;
import com.sa.accommodation_service.hotel.application.outputports.persistence.FindAllHotels;
import com.sa.accommodation_service.hotel.domain.Hotel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@Component
@UseCase
public class GetAllHotelsImpl implements GetAllHotels {
    
    private final FindAllHotels findAllHotels;

    @Override
    @Transactional
    public List<Hotel> getAll(@Valid HotelSearchDTO hotelSearchDTO) {
        return findAllHotels.findAll(hotelSearchDTO);
    }

}
