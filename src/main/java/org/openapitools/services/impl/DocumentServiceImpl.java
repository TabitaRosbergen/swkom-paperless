package org.openapitools.services.impl;

import org.openapitools.configuration.RabbitMQConfig;
import org.openapitools.persistence.entities.DocumentsDocumentEntity;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.model.Document;
import org.openapitools.persistence.entities.DocumentsStoragepathEntity;
import org.openapitools.persistence.repositories.DocumentsDocumentRepository;
import org.openapitools.services.requestservices.MinioService;
import org.openapitools.services.mapper.DocumentMapper;
import org.openapitools.services.requestservices.DocumentService;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentsDocumentRepository documentsDocumentRepository;
    private final DocumentMapper documentMapper;
    private final RabbitTemplate rabbitTemplate;
    private final MinioService minioService;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    public DocumentServiceImpl(DocumentsDocumentRepository documentRepository, DocumentMapper documentMapper, RabbitTemplate rabbitTemplate, MinioService minioService) {
        this.documentsDocumentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.minioService = minioService;
    }

    @Override
    public void uploadDocument(Document document, MultipartFile multipartFile) {
        document.setCreated(OffsetDateTime.now());
        document.setAdded(OffsetDateTime.now());
        document.setModified(OffsetDateTime.now());
        document.setContent(JsonNullable.of(""));
        document.setAdded(OffsetDateTime.now());

        DocumentsDocumentEntity documentToBeSaved = documentMapper.dtoToEntity(document);

        documentToBeSaved.setChecksum("checksum");
        documentToBeSaved.setStorageType("minio");
        documentToBeSaved.setMimeType("mimeType");

        // create the bucket if it doesn't exist.
        minioService.createBucket();

        // generate a random id for the document path
        String path = UUID.randomUUID() + "/" + multipartFile.getOriginalFilename();

        // upload the document to minio
        minioService.uploadDocument(multipartFile, path);

        //create a StoragePath for the document
        DocumentsStoragepathEntity storagePath = new DocumentsStoragepathEntity();
        storagePath.setPath(path);
        storagePath.setMatchingAlgorithm(1);
        storagePath.setMatch("match");
        storagePath.setIsInsensitive(false);
        storagePath.setName(multipartFile.getOriginalFilename());
        storagePath.setOwner(null);

        // save the storage path to the postgres database
        documentToBeSaved.setStoragePath(storagePath);

        // save the document to the postgres database
        documentsDocumentRepository.save(documentToBeSaved);

        //send a message to the queue with the path to the document
        rabbitTemplate.convertAndSend(RabbitMQConfig.MESSAGE_IN_QUEUE, path);
    }
}
