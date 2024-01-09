package org.openapitools.services.interfaces;

import org.openapitools.model.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    void uploadDocument(Document document, MultipartFile multipartFile);

    List<Document> getDocuments();

    List<Document> getDocumentsByContentString(String contentString);
}
