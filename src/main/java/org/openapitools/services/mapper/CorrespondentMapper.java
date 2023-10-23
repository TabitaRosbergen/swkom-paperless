package org.openapitools.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.entities.DocumentsCorrespondentEntity;
import org.openapitools.entities.DocumentsTagEntity;
import org.openapitools.model.Correspondent;
import org.openapitools.model.DocTag;

@Mapper
public interface CorrespondentMapper extends BaseMapper<DocumentsCorrespondentEntity, Correspondent> {
    CorrespondentMapper INSTANCE = Mappers.getMapper(CorrespondentMapper.class);
}
