package org.openapitools.services;

import io.minio.*;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MinioService {

    @Value("${minio.bucketname}")
    private String bucketName;
    private final MinioClient minioClient;

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

    public void uploadDocument(String documentName, MultipartFile multipartFile) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(this.bucketName)
                            .object(documentName)
                            .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                            .build());

            //get all documents from minio storage and print them
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(this.bucketName)
                            .build());

            for (Result<Item> result : results) {
                Item item = result.get();
                System.out.println("Retrieved item: " + item.lastModified() + ", " + item.size() + ", " + item.objectName());
            }

        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
