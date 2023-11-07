package org.openapitools.services.requestservices;

import org.openapitools.entities.DocumentsDocumentEntity;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.model.Document;
import org.openapitools.repositories.DocumentsDocumentRepository;
import org.openapitools.services.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService{

    private final DocumentsDocumentRepository documentsDocumentRepository;
    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    public DocumentServiceImpl(DocumentsDocumentRepository documentRepository) {
        this.documentsDocumentRepository = documentRepository;
    }

    @Override
    public void uploadDocument(Document document, List<MultipartFile> multipartFiles) {


        document.setCreated(OffsetDateTime.now());
        document.setAdded(OffsetDateTime.now());
        document.setModified(OffsetDateTime.now());
        document.setContent(JsonNullable.of(""));
        document.setAdded(OffsetDateTime.now());

        DocumentsDocumentEntity documentToBeSaved = documentMapper.dtoToEntity(document);

        documentToBeSaved.setChecksum("checksum");
        documentToBeSaved.setStorageType("pdf");
        documentToBeSaved.setMimeType("pdf");

        documentsDocumentRepository.save(documentToBeSaved);
    }
}
