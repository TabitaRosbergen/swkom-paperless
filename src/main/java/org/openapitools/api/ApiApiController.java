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

    @Autowired
    public ApiApiController(DocumentServiceImpl documentServiceImpl, NativeWebRequest request) {
        this.documentServiceImpl = documentServiceImpl;
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> uploadDocument(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> multipartFiles) {
        try {
            MultipartFile multipartFile = multipartFiles.get(0);

            String name = multipartFile.getOriginalFilename();
            Document document = new Document();
            document.setTitle(JsonNullable.of(title == null ? name : title));
            document.setOriginalFileName(JsonNullable.of(name));
            document.setCreated(created);
            document.setDocumentType(JsonNullable.of(documentType));
            document.setTags(JsonNullable.of(tags));
            document.setCorrespondent(JsonNullable.of(correspondent));

            documentServiceImpl.uploadDocument(document, multipartFile);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }


}
