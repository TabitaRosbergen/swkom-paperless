package org.openapitools.services.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.persistence.entities.DocumentsDocumentEntity;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.model.Document;

import static org.junit.jupiter.api.Assertions.*;

class DocumentMapperTest {

    private DocumentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = DocumentMapper.INSTANCE;
    }

    @Test
    void testEntityToDto() {
        // Create a DocumentsDocumentEntity
        DocumentsDocumentEntity entity = new DocumentsDocumentEntity();
        entity.setId(1);
        entity.setTitle("Sample Document");
        // Set other properties as needed

        // Call the mapper to map the entity to a DTO
        Document dto = mapper.entityToDto(entity);

        // Verify that the DTO is correctly mapped
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getTitle(), dto.getTitle().get());
        // Verify other properties as well
    }

    @Test
    void testDtoToEntity() {
        // Create a Document DTO
        Document dto = new Document();

        dto.setId(1);
        dto.setTitle(JsonNullable.of("Sample Document"));

        // Call the mapper to map the DTO to an entity
        DocumentsDocumentEntity entity = mapper.dtoToEntity(dto);

        // Verify that the entity is correctly mapped
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getTitle().get(), entity.getTitle());
        // Verify other properties as well
    }

}