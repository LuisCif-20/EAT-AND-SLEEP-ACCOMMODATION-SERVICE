package com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.accommodation_service.common.infrastructure.annotations.InputAdapter;
import com.sa.accommodation_service.hotel.application.inputports.CreateHotel;
import com.sa.accommodation_service.hotel.application.inputports.GetAllHotels;
import com.sa.accommodation_service.hotel.application.inputports.GetHotelById;
import com.sa.accommodation_service.hotel.application.inputports.HotelActivation;
import com.sa.accommodation_service.hotel.application.inputports.UpdateHotel;
import com.sa.accommodation_service.hotel.domain.Hotel;
import com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto.CreateHotelRequestDTO;
import com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto.CreateHotelResponseDTO;
import com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto.GetHotelByIdResponseDTO;
import com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto.HotelResponse;
import com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto.HotelSearchRequestDTO;
import com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto.UpdateHotelRequestDTO;
import com.sa.accommodation_service.hotel.infrastructure.inputadapters.rest.dto.UpdateHotelResponseDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RequestMapping("/v1.0/hotels")
@RestController
@InputAdapter
public class HotelController {
    
    private final CreateHotel createHotel;
    private final UpdateHotel updateHotel;
    private final GetHotelById getHotelById;
    private final GetAllHotels getAllHotels;
    private final HotelActivation hotelActivation;

    @PostMapping
    public ResponseEntity<CreateHotelResponseDTO> create(
            @ModelAttribute CreateHotelRequestDTO createHotelRequestDTO) {
        final Hotel hotel = createHotel.create(createHotelRequestDTO.toCreateHotelDTO());
        return ResponseEntity.status(HttpStatus.CREATED).body(CreateHotelResponseDTO.fromDomain(hotel));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateHotelResponseDTO> update(
            @PathVariable UUID id,
            @ModelAttribute UpdateHotelRequestDTO updateHotelRequestDTO) {
        final Hotel hotel = updateHotel.update(id, updateHotelRequestDTO.toUpdateHotelDTO());
        return ResponseEntity.status(HttpStatus.OK).body(UpdateHotelResponseDTO.fromDomain(hotel));
    }    

    @GetMapping("/{id}")
    public ResponseEntity<GetHotelByIdResponseDTO> getById(@PathVariable UUID id) {
        final Hotel hotel = getHotelById.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(GetHotelByIdResponseDTO.fromDomain(hotel));
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAll(HotelSearchRequestDTO hotelSearchRequestDTO) {
        final List<HotelResponse> hotels = getAllHotels.getAll(hotelSearchRequestDTO.toHotelSearchDTO())
                .stream()
                .map(HotelResponse::fromDomain)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(hotels);
    }
    
    @PatchMapping("/activation/{id}")
    public ResponseEntity<Void> toggle(@PathVariable UUID id) {
        hotelActivation.toggle(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    

}
