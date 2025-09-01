package com.sa.accommodation_service.common.application.outputports.cloud;

import org.springframework.web.multipart.MultipartFile;

import com.sa.accommodation_service.common.application.annotations.OutputPort;

@OutputPort
public interface UploadImage {

    public String upload(MultipartFile multipartFile);
    
}
