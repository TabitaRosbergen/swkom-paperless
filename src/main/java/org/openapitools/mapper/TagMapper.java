package org.openapitools.mapper;

import org.mapstruct.Mapper;
import org.openapitools.entities.DocumentsCorrespondentEntity;
import org.openapitools.entities.DocumentsTagEntity;
import org.openapitools.model.Correspondent;
import org.openapitools.model.DocTag;
import org.openapitools.model.Document;
import org.openapitools.model.NewCorrespondent;

@Mapper
public interface TagMapper {
    DocumentsTagEntity sourceToDestination(DocTag source);
    DocTag destinationToSource(DocumentsTagEntity destination);
}
