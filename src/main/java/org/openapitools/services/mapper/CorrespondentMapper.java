package org.openapitools.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.persistence.entities.DocumentsCorrespondentEntity;
import org.openapitools.model.Correspondent;

@Mapper
public interface CorrespondentMapper extends BaseMapper<DocumentsCorrespondentEntity, Correspondent> {
    CorrespondentMapper INSTANCE = Mappers.getMapper(CorrespondentMapper.class);
}
