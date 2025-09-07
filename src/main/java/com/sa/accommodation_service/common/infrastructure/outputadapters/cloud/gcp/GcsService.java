package com.sa.accommodation_service.common.infrastructure.outputadapters.cloud.gcp;

import org.springframework.stereotype.Component;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.sa.accommodation_service.common.application.inputports.dto.FileDataDTO;
import com.sa.accommodation_service.common.application.outputports.cloud.DeleteFile;
import com.sa.accommodation_service.common.application.outputports.cloud.UploadFile;
import com.sa.accommodation_service.common.infrastructure.annotations.OutputAdapter;
import com.sa.accommodation_service.common.infrastructure.outputadapters.cloud.gcp.properties.GcsProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@OutputAdapter
public class GcsService implements UploadFile, DeleteFile{

    private final GcsProperties gcsProperties;
    private final Storage storage;

    @Override
    public void upload(FileDataDTO fileDataDTO) {
        final BlobId blobId = BlobId
                .of(gcsProperties.getStorage().getBucketName(), fileDataDTO.fileName());
        final BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(fileDataDTO.contentType())
                .build();
        storage.create(blobInfo, fileDataDTO.content());
    }

    @Override
    public void delete(String fileName) {
        BlobId blobId = BlobId.of(gcsProperties.getStorage().getBucketName(), fileName);
        Blob blob = storage.get(blobId);
        if (blob == null || !blob.exists()) {
            return;
        }
        storage.delete(blobId);
    }
    
}
