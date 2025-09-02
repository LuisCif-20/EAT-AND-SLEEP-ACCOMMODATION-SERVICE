package com.sa.accommodation_service.room.application.facotry;

import org.springframework.stereotype.Component;

import com.sa.accommodation_service.common.application.factory.utils.FactoryUtil;
import com.sa.accommodation_service.hotel.domain.Hotel;
import com.sa.accommodation_service.room.application.dto.UpdateRoomDTO;
import com.sa.accommodation_service.room.domain.Room;

@Component
public class RoomFactory {

    public Room updateFromDTO(UpdateRoomDTO updateRoomDTO, Hotel hotel, String photo, Room room) {
        return new Room(
                room.getId().value(),
                FactoryUtil
                        .updateValueIfNotNull(hotel, room.getHotel()),
                FactoryUtil
                        .updateValueIfNotNull(updateRoomDTO.roomNumber(), room.getRoomNumber().value()),
                FactoryUtil
                        .updateValueIfNotNull(updateRoomDTO.description(), room.getDescription()),
                FactoryUtil
                        .updateValueIfNotNull(updateRoomDTO.pricePerNight(), room.getPricePerNight().value()),
                FactoryUtil
                        .updateValueIfNotNull(updateRoomDTO.maintenanceCost(), room.getMaintenanceCost().value()),
                FactoryUtil
                        .updateValueIfNotNull(photo, room.getPhoto()),
                room.isAvailable(),
                room.isActive());
    }

}
