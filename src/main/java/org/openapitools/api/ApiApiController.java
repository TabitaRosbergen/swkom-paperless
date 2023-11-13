package org.openapitools.api;

import io.minio.*;
import io.minio.messages.Item;
import org.openapitools.configuration.RabbitMQConfig;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.model.Document;
import org.openapitools.services.impl.DocumentServiceImpl;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-10T06:36:40.060738Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
@CrossOrigin(origins = "http://localhost:8080")
public class ApiApiController implements ApiApi {

    private final DocumentServiceImpl documentServiceImpl;
    private final NativeWebRequest request;
    private final RabbitTemplate rabbitTemplate;

    private final MinioClient minioClient;

    @Autowired
    public ApiApiController(DocumentServiceImpl documentServiceImpl, NativeWebRequest request, RabbitTemplate rabbitTemplate, MinioClient minioClient) {
        this.documentServiceImpl = documentServiceImpl;
        this.request = request;
        this.rabbitTemplate = rabbitTemplate;
        this.minioClient = minioClient;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> uploadDocument(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> multipartFiles) {
        try {

            String name = multipartFiles.get(0).getOriginalFilename();
            Document document = new Document();
            document.setTitle(JsonNullable.of(title == null ? name : title));
            document.setOriginalFileName(JsonNullable.of(name));
            document.setCreated(created);
            document.setDocumentType(JsonNullable.of(documentType));
            document.setTags(JsonNullable.of(tags));
            document.setCorrespondent(JsonNullable.of(correspondent));

            //upload the document to the postgres database
            documentServiceImpl.uploadDocument(document, multipartFiles);

            // create the bucket if it doesn't exist.
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("test2").build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("test2").build());
            } else {
                System.out.println("Bucket 'test2' already exists.");
            }

            //upload the document to the minio storage
            minioClient.putObject(
              PutObjectArgs
                .builder()
                .bucket("test2")
                .object(name)
                .stream(multipartFiles.get(0).getInputStream(), multipartFiles.get(0).getSize(), -1)
                .build());

            //get all documents from minio storage and print them
             Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket("test2")
                            .build());

                for (Result<Item> result : results) {
                    Item item = result.get();
                    System.out.println("Retrieved item: " + item.lastModified() + ", " + item.size() + ", " + item.objectName());
                }

            //send a message to the queue that a document has been uploaded
            rabbitTemplate.convertAndSend(RabbitMQConfig.ECHO_IN_QUEUE_NAME, document.toString());

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }


}
