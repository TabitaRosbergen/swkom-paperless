package org.openapitools.services.interfaces;

import org.openapitools.model.Document;
import org.openapitools.services.impl.DocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    void uploadDocument(Document document, MultipartFile multipartFile);

    List<Document> getDocuments();

    Page<DocumentDTO> getDocumentsByContentString(String contentString);
}
