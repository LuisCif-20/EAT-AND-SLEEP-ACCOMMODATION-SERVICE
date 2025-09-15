package com.sa.accommodation_service.lodging.application.outputports.persistence;

import com.sa.accommodation_service.common.application.annotations.OutputPort;
import com.sa.accommodation_service.lodging.domain.Lodging;

@OutputPort
public interface SaveLodging {
    
    public Lodging save(Lodging lodging);

}
