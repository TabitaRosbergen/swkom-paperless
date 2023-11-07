package org.openapitools.api;

import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.model.Document;
import org.openapitools.services.requestservices.DocumentServiceImpl;


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

            String name = multipartFiles.get(0).getOriginalFilename();
            Document document = new Document();
            document.setTitle(JsonNullable.of(title == null ? name : title));
            System.out.println("!!!!!!!!!!!In upload: title: " +document.getTitle()); //TEST
            document.setOriginalFileName(JsonNullable.of(name));
            document.setCreated(created);
            document.setDocumentType(JsonNullable.of(documentType));
            document.setTags(JsonNullable.of(tags));
            document.setCorrespondent(JsonNullable.of(correspondent));

            documentServiceImpl.uploadDocument(document, multipartFiles);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }


}
