package org.openapitools.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.Document;
import org.openapitools.persistence.entities.DocumentsDocumentEntity;
import org.openapitools.persistence.repositories.DocumentsDocumentRepository;
import org.openapitools.services.mapper.DocumentMapper;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentServiceImplTest {

    @Mock
    private DocumentsDocumentRepository documentsDocumentRepository;

    @Mock
    private DocumentMapper documentMapper;

    @InjectMocks
    private DocumentServiceImpl documentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getDocuments() {
        // Mock data
        List<DocumentsDocumentEntity> documentEntities = Collections.singletonList(new DocumentsDocumentEntity());

        // Mock the behavior of dependencies
        when(documentsDocumentRepository.findAll()).thenReturn(documentEntities);
        when(documentMapper.entityToDto(Mockito.any())).thenReturn(new Document());

        // Call the method
        List<Document> documents = documentService.getDocuments();

        // Verify the result
        assertEquals(1, documents.size()); // Check if the list is not empty

        // Verify the interactions
        verify(documentsDocumentRepository, times(1)).findAll();
        // Verify other interactions as needed
    }
}
