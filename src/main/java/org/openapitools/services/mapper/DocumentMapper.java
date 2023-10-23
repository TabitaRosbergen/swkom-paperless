package org.openapitools.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.entities.DocumentsDocumentEntity;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.model.Document;

import java.util.List;


@Mapper
public interface DocumentMapper {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    List<Document> toDto(List<DocumentsDocumentEntity> entity);
    Document toDto(DocumentsDocumentEntity entity);
    DocumentsDocumentEntity toEntity(Document dto);
}
