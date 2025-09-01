package com.sa.accommodation_service.hotel.infrastructure.outputadapters.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "hotel")
@Entity
public class HotelEntity {
    
    @Id
    private UUID id;
    
    @Column
    private String name;
    
    @Column
    private String address;
    
    @Column
    private String city;
    
    @Column
    private String phoneNumber;
    
    @Column
    private String photo;
    
    @Column
    private Boolean active;

}
