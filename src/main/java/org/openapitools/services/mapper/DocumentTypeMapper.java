package org.openapitools.services.mapper;

import org.mapstruct.factory.Mappers;
import org.openapitools.persistence.entities.DocumentsDocumenttypeEntity;
import org.openapitools.model.DocumentType;

public interface DocumentTypeMapper extends BaseMapper<DocumentsDocumenttypeEntity, DocumentType> {
    DocumentTypeMapper INSTANCE = Mappers.getMapper(DocumentTypeMapper.class);
}
