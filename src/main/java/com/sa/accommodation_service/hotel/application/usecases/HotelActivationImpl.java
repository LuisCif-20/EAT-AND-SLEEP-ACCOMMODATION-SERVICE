package com.sa.accommodation_service.hotel.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.accommodation_service.common.application.annotations.UseCase;
import com.sa.accommodation_service.common.infrastructure.exceptions.EntityNotFoundException;
import com.sa.accommodation_service.hotel.application.inputports.HotelActivation;
import com.sa.accommodation_service.hotel.application.outputports.persistence.FindHotelById;
import com.sa.accommodation_service.hotel.application.outputports.persistence.SaveHotel;
import com.sa.accommodation_service.hotel.domain.Hotel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@UseCase
public class HotelActivationImpl implements HotelActivation {
    
    private final FindHotelById findHotelById;
    private final SaveHotel saveHotel;

    @Override
    @Transactional
    public void toggle(UUID id) {
        final Hotel hotel = findHotelById.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe un hotel con el id: " + id));
        hotel.toggleActive();
        saveHotel.save(hotel);
    }

}
