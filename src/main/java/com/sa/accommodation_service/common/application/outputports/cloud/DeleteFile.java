package com.sa.accommodation_service.common.application.outputports.cloud;

import com.sa.accommodation_service.common.application.annotations.OutputPort;

@OutputPort
public interface DeleteFile {
    
    public void delete(String fileName);

}
