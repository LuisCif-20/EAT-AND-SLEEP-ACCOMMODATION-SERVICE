package com.sa.accommodation_service.lodging.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.sa.accommodation_service.common.domain.annotations.Default;
import com.sa.accommodation_service.common.domain.annotations.DomainEntity;
import com.sa.accommodation_service.common.domain.valueobjects.Id;
import com.sa.accommodation_service.common.domain.valueobjects.Money;
import com.sa.accommodation_service.lodging.domain.valueobjects.DateRange;
import com.sa.accommodation_service.lodging.domain.valueobjects.LodgingStatus;
import com.sa.accommodation_service.room.domain.Room;

import lombok.Getter;

@Getter
@DomainEntity
public class Lodging {
    
    private Id id;
    private Room room;
    private Id customerId;
    private DateRange stayPeriod;
    private LodgingStatus status;
    private Money totalPrice;

    @Default
    public Lodging(UUID id, Room room, UUID customerId, LocalDateTime checkIn,
            LocalDateTime checkOut, String status, BigDecimal totalPrice) {
        this.id = new Id(id);
        this.room = room;
        this.customerId = new Id(customerId);
        this.stayPeriod = new DateRange(checkIn, checkOut);
        this.status = new LodgingStatus(status);
        this.totalPrice = new Money(totalPrice);
    }

    public Lodging(Room room, UUID customerId, LocalDateTime checkIn,
            LocalDateTime checkOut, String status) {
        this.id = Id.generate();
        this.room = room;
        this.customerId = new Id(customerId);
        this.stayPeriod = new DateRange(checkIn, checkOut);
        this.status = LodgingStatus.createLodgingStatus(status);
        calculateTotalPrice();
    }

    public long getTotalNights() {
        return (int) ChronoUnit.DAYS.between(
                this.stayPeriod.checkIn().toLocalDate(), 
                this.stayPeriod.checkOut().toLocalDate());
    }

    public void calculateTotalPrice() {
        this.totalPrice = new Money(
                room.getPricePerNight().value()
                        .multiply(BigDecimal.valueOf(getTotalNights())));
    }

}
