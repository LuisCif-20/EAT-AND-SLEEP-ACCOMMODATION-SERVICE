package com.sa.accommodation_service.common.infrastructure.outputadapters.cloud.gcp.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "cloud.gcp")
@Component
public class GcsProperties {

    private Storage storage;

    @Getter
    @Setter
    public static class Storage {
        private String bucketName;
    }
    
}
