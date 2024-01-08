package org.openapitools.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.Document;
import org.openapitools.services.impl.DocumentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ApiApiControllerTest {

    @Mock
    private DocumentServiceImpl documentService;

    @Mock
    private NativeWebRequest nativeWebRequest;

    @InjectMocks
    private ApiApiController apiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void customGetDocuments_shouldReturnDocuments() {
        Document doc1 = new Document();
        doc1.setId(1);
        Document doc2 = new Document();
        doc2.setId(2);
        // Mocking the documentService to return a list of documents
        List<Document> mockDocuments = Arrays.asList(
                doc1,doc2
        );
        when(documentService.getDocuments()).thenReturn(mockDocuments);

        // Calling the controller method
        ResponseEntity<ObjectNode> responseEntity = apiController.customGetDocuments();

        // Assertions
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockDocuments.size(), responseEntity.getBody().size());
    }

    @Test
    void uploadDocument_shouldUploadDocument() {
        // Mocking necessary data
        String title = "Test Document";
        OffsetDateTime created = OffsetDateTime.now();
        Integer documentType = 1;
        List<Integer> tags = Arrays.asList(1, 2);
        Integer correspondent = 1;

        // Creating a mock MultipartFile
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file", "test.txt", "text/plain", "Hello, World!".getBytes()
        );

        // Mocking the documentService.uploadDocument method
        Mockito.doNothing().when(documentService).uploadDocument(any(Document.class), any(MultipartFile.class));

        // Calling the controller method
        ResponseEntity<Void> responseEntity = apiController.uploadDocument(title, created, documentType, tags, correspondent, Collections.singletonList(mockMultipartFile));

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
