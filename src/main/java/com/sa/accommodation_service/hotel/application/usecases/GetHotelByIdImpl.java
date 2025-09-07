package com.sa.accommodation_service.hotel.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.hotel.application.inputports.gethotelbyid.GetHotelById;
import com.sa.accommodation_service.hotel.application.outputports.persistence.FindHotelById;
import com.sa.accommodation_service.hotel.domain.Hotel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@UseCase
public class GetHotelByIdImpl implements GetHotelById {

    private final FindHotelById findHotelById;

    @Override
    @Transactional
    public Hotel getById(UUID id) {
        final Hotel hotel = findHotelById.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe un hotel con el id: " + id));
        return hotel;
    }

}
