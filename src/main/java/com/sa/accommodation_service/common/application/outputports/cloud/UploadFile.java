package com.sa.accommodation_service.common.application.outputports.cloud;

import com.sa.accommodation_service.common.application.annotations.OutputPort;
import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;

@OutputPort
public interface UploadFile {

    public void upload(FileDataDTO fileDataDTO);
    
}
