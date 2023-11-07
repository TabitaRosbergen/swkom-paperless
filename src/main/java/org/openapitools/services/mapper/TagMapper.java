package org.openapitools.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.entities.DocumentsTagEntity;
import org.openapitools.model.DocTag;

@Mapper
public interface TagMapper extends BaseMapper<DocumentsTagEntity, DocTag> {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
}
