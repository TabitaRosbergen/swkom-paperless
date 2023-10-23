package org.openapitools.services.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.openapitools.entities.DocumentsCorrespondentEntity;
import org.openapitools.model.Correspondent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-23T17:18:23+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (SAP SE)"
)
public class CorrespondentMapperImpl implements CorrespondentMapper {

    @Override
    public List<Correspondent> toDto(List<DocumentsCorrespondentEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<Correspondent> list = new ArrayList<Correspondent>( entity.size() );
        for ( DocumentsCorrespondentEntity documentsCorrespondentEntity : entity ) {
            list.add( toDto( documentsCorrespondentEntity ) );
        }

        return list;
    }

    @Override
    public Correspondent toDto(DocumentsCorrespondentEntity entity) {
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
    public DocumentsCorrespondentEntity toEntity(Correspondent dto) {
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
