package org.openapitools.services.impl;

import com.google.common.collect.Lists;
import org.openapitools.configuration.RabbitMQConfig;
import org.openapitools.persistence.entities.DocumentsDocumentEntity;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.model.Document;
import org.openapitools.persistence.entities.DocumentsStoragepathEntity;
import org.openapitools.persistence.repositories.DocumentsDocumentRepository;
import org.openapitools.services.mapper.DocumentMapper;
import org.openapitools.services.interfaces.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.amqp.core.Message;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentsDocumentRepository documentsDocumentRepository;
    private final DocumentMapper documentMapper;
    private final RabbitTemplate rabbitTemplate;
    private final MinioService minioService;
    private final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final ESDocumentRepository esDocumentRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public DocumentServiceImpl(DocumentsDocumentRepository documentRepository, DocumentMapper documentMapper, RabbitTemplate rabbitTemplate, MinioService minioService, ESDocumentRepository esDocumentRepository, ElasticsearchOperations elasticsearchOperations) {
        this.documentsDocumentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.minioService = minioService;
        this.esDocumentRepository = esDocumentRepository;
        this.elasticsearchOperations = elasticsearchOperations;
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

        // TODO try with only one call, because now it seems like both calls work
        // for some unknown reason every other document would fail so we call this two times, don't judge
        // here be dragons
        rabbitTemplate.convertAndSend(RabbitMQConfig.MESSAGE_IN_QUEUE, path);
        Message responseMessage = rabbitTemplate.receive(RabbitMQConfig.MESSAGE_OUT_QUEUE, 6000); // Adjust timeout as needed

        //send a message to the queue with the path to the document
        rabbitTemplate.convertAndSend(RabbitMQConfig.MESSAGE_IN_QUEUE, path);
        responseMessage = rabbitTemplate.receive(RabbitMQConfig.MESSAGE_OUT_QUEUE, 6000); // Adjust timeout as needed

        if (responseMessage == null) {
            logger.error("No response from the queue ---------------------------------------------------------------------------------------------------------------------");
        } else {
            String responseJson = new String(responseMessage.getBody());
            logger.info("Response from the queue: " + responseJson);

            documentToBeSaved.setContent(responseJson);
        }

        //beide creates werfen die selbe exception: org.springframework.data.elasticsearch.NoSuchIndexException: Index null not found.;
        // nested exception is ElasticsearchException[java.util.concurrent.ExecutionException: java.net.ConnectException: Connection refused];
        // nested: ExecutionException[java.net.ConnectException: Connection refused];
        // nested: ConnectException[Connection refused];
        //elasticsearchOperations.indexOps(IndexCoordinates.of("files")).create();
//        elasticsearchOperations.indexOps(DocumentDTO.class).create();

//        DocumentDTO DocumentDTO = new DocumentDTO();
//        DocumentDTO.setId(documentToBeSaved.getId().toString());
//        DocumentDTO.setContent(documentToBeSaved.getContent());
//
//        ESDocumentRepository.save(DocumentDTO);
//
//        //find all documents in the ESDocumentRepository that match the search query
//        Page<DocumentDTO> documentDTOs = ESDocumentRepository.findByContentContains(documentToBeSaved.getContent(), PageRequest.of(0, 10));
//
//        //print the number of documents that match the search query
//        logger.info("Number of documents that match the search query: " + documentDTOs.getTotalElements());
//
        // save the document to the postgres database
        documentsDocumentRepository.save(documentToBeSaved);

        logger.info("id = " + documentToBeSaved.getId());
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(String.valueOf(documentToBeSaved.getId()));
        documentDTO.setContent(documentToBeSaved.getContent());

        esDocumentRepository.save(documentDTO);

        //
//        try {
////            Page<DocumentDTO> documentDTOs = esService.findByContentContainsCustom(documentToBeSaved.getContent(), PageRequest.of(0, 10));
//            List<DocumentDTO> documentDTOs = Lists.newArrayList(esDocumentRepository.findAll());
//            logger.info("Number of documents that match the search query: " + documentDTOs.size());
//            documentDTOs.forEach(d -> logger.info(d.toString()));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public List<Document> getDocuments() {
        List<DocumentsDocumentEntity> documentsDocumentEntities = documentsDocumentRepository.findAll();

        //print the number of documents in the database
        logger.info("Number of documents in the database: " + documentsDocumentEntities.size());

        List<Document> documents = new java.util.ArrayList<>(Collections.emptyList());

        //convert each DocumentsDocumentEntity to a Document
        for (DocumentsDocumentEntity documentsDocumentEntity : documentsDocumentEntities) {
            documents.add(documentMapper.entityToDto(documentsDocumentEntity));
        }

        return documents;
    }
}
