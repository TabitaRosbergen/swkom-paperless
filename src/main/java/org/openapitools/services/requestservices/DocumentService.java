package org.openapitools.services.requestservices;

import org.openapitools.model.Document;
import org.openapitools.model.GetDocuments200Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    void uploadDocument(Document document, MultipartFile multipartFile);

    List<Document> getDocuments();
}
