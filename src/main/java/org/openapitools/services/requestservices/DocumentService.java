package org.openapitools.services.requestservices;

import org.openapitools.model.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface DocumentService {
    void uploadDocument(Document document, MultipartFile multipartFile);
}
