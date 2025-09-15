package com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.entity.HotelEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "room")
@Entity
public class RoomEntity {
    
    @Id
    private UUID id;

    @Column
    private String roomNumber;
    
    @Column
    private String description;
    
    @Column
    private BigDecimal pricePerNight;
    
    @Column
    private BigDecimal maintenanceCost;
    
    @Column
    private String photo;
    
    @Column
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

}
