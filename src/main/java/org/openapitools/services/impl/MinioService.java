package org.openapitools.services.impl;

import io.minio.*;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;


@Service
public class MinioService {

    @Value("${minio.bucketname}")
    private String bucketName;
    private final MinioClient minioClient;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(MinioService.class);

    @Autowired
    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void createBucket() {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(this.bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(this.bucketName).build());
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public void uploadDocument(MultipartFile file, String nameInBucket) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(this.bucketName)
                            .object(nameInBucket)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build());

        } catch (Exception e) {
            logger.error("Error occurred: " + e);
        }

        logger.info("Successfully uploaded document to minio storage");
    }

    //get the document from minio storage
    public File getDocument(String path_in_bucket) {
        InputStream stream = null;
        try {
            stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(this.bucketName)
                            .object(path_in_bucket)
                            .build());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            logger.error("MinioService could not get document from minio: " + e);
        }

        //convert the stream to a temporary file
        File tempFile = null;
        try {
            if (stream != null) {
                tempFile = File.createTempFile("temp", ".tmp");
                FileUtils.copyInputStreamToFile(stream, tempFile);
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            logger.error("MinioService could not convert stream to temporary file: " + e);
        }

        logger.info("Successfully retrieved document from minio storage");

        return tempFile;
    }
}
