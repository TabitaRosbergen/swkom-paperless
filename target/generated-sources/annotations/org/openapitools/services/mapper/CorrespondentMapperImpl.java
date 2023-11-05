package org.openapitools.services.mapper;

import javax.annotation.Generated;
import org.openapitools.entities.DocumentsCorrespondentEntity;
import org.openapitools.model.Correspondent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-05T17:48:46+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (SAP SE)"
)
public class CorrespondentMapperImpl implements CorrespondentMapper {

    @Override
    public Correspondent entityToDto(DocumentsCorrespondentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Correspondent correspondent = new Correspondent();

        if ( entity.getId() != null ) {
            correspondent.setId( entity.getId().longValue() );
        }
        correspondent.setName( map( entity.getName() ) );
        correspondent.setMatch( map( entity.getMatch() ) );
        if ( entity.getMatchingAlgorithm() != null ) {
            correspondent.setMatchingAlgorithm( entity.getMatchingAlgorithm().longValue() );
        }
        correspondent.setIsInsensitive( entity.getIsInsensitive() );

        return correspondent;
    }

    @Override
    public DocumentsCorrespondentEntity dtoToEntity(Correspondent dto) {
        if ( dto == null ) {
            return null;
        }

        DocumentsCorrespondentEntity documentsCorrespondentEntity = new DocumentsCorrespondentEntity();

        if ( dto.getId() != null ) {
            documentsCorrespondentEntity.setId( dto.getId().intValue() );
        }
        documentsCorrespondentEntity.setName( map( dto.getName() ) );
        documentsCorrespondentEntity.setMatch( map( dto.getMatch() ) );
        if ( dto.getMatchingAlgorithm() != null ) {
            documentsCorrespondentEntity.setMatchingAlgorithm( dto.getMatchingAlgorithm().intValue() );
        }
        documentsCorrespondentEntity.setIsInsensitive( dto.getIsInsensitive() );

        return documentsCorrespondentEntity;
    }
}
