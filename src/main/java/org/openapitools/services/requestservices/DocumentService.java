package org.openapitools.services.requestservices;

import org.openapitools.model.Document;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    void uploadDocument(Document document, MultipartFile multipartFile);
}
