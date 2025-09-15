package com.sa.accommodation_service.lodging.infrastructure.outputadapters.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.sa.accommodation_service.room.infrastructure.outputadapters.persistence.entity.RoomEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "lodging")
@Entity
public class LodgingEntity {
    
    @Id
    private UUID id;

    @Column
    private UUID customerId;

    @Column
    private LocalDateTime checkIn;

    @Column
    private LocalDateTime checkOut;

    @Enumerated(EnumType.STRING)
    @Column
    private LodgingEntityStatus status;

    @Column
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

}
