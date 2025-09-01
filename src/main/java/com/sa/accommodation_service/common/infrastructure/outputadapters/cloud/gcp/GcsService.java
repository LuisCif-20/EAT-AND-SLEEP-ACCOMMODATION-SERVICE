package com.sa.accommodation_service.common.infrastructure.outputadapters.cloud.gcp;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.sa.accommodation_service.common.application.outputports.cloud.DeleteFile;
import com.sa.accommodation_service.common.application.outputports.cloud.UploadImage;
import com.sa.accommodation_service.common.infrastructure.annotations.OutputAdapter;
import com.sa.accommodation_service.common.infrastructure.exceptions.FileUploadException;
import com.sa.accommodation_service.common.infrastructure.outputadapters.cloud.gcp.properties.GcsProperties;
import com.sa.accommodation_service.common.infrastructure.outputadapters.cloud.utils.ImageFileValidator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@OutputAdapter
public class GcsService implements UploadImage, DeleteFile{

    private final GcsProperties gcsProperties;
    private final Storage storage;

    @Override
    public void delete(String fileName) {
        final BlobId blobId = BlobId.of(gcsProperties.getStorage().getBucketName(), fileName);
        final Blob blob = storage.get(blobId);
        if (blob == null || !blob.exists()) {
            return;
        }
        storage.delete(blobId);
    }

    @Override
    public String upload(MultipartFile multipartFile) {
        try {
            final String fileName = ImageFileValidator
                    .generateValidImageFileName(multipartFile);
            final BlobId blobId = BlobId
                    .of(gcsProperties.getStorage().getBucketName(), fileName);
            final BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(multipartFile.getContentType())
                    .build();
            storage.create(blobInfo, multipartFile.getBytes());
            return fileName;
        } catch (IOException e) {
            throw new FileUploadException("Error al manipular el archivo seleccionado");
        }
    }


    
}
