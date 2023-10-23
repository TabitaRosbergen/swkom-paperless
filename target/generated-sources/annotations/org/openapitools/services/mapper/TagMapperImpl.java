package org.openapitools.services.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.openapitools.entities.DocumentsTagEntity;
import org.openapitools.model.DocTag;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-23T17:18:23+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (SAP SE)"
)
public class TagMapperImpl implements TagMapper {

    @Override
    public List<DocTag> toDto(List<DocumentsTagEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<DocTag> list = new ArrayList<DocTag>( entity.size() );
        for ( DocumentsTagEntity documentsTagEntity : entity ) {
            list.add( toDto( documentsTagEntity ) );
        }

        return list;
    }

    @Override
    public DocTag toDto(DocumentsTagEntity entity) {
        if ( entity == null ) {
            return null;
        }

        DocTag docTag = new DocTag();

        if ( entity.getId() != null ) {
            docTag.setId( entity.getId().longValue() );
        }
        docTag.setName( map( entity.getName() ) );
        docTag.setColor( map( entity.getColor() ) );
        docTag.setMatch( map( entity.getMatch() ) );
        if ( entity.getMatchingAlgorithm() != null ) {
            docTag.setMatchingAlgorithm( entity.getMatchingAlgorithm().longValue() );
        }
        docTag.setIsInsensitive( entity.getIsInsensitive() );
        docTag.setIsInboxTag( entity.getIsInboxTag() );

        return docTag;
    }

    @Override
    public DocumentsTagEntity toEntity(DocTag dto) {
        if ( dto == null ) {
            return null;
        }

        DocumentsTagEntity documentsTagEntity = new DocumentsTagEntity();

        if ( dto.getId() != null ) {
            documentsTagEntity.setId( dto.getId().intValue() );
        }
        documentsTagEntity.setName( map( dto.getName() ) );
        documentsTagEntity.setMatch( map( dto.getMatch() ) );
        if ( dto.getMatchingAlgorithm() != null ) {
            documentsTagEntity.setMatchingAlgorithm( dto.getMatchingAlgorithm().intValue() );
        }
        documentsTagEntity.setIsInsensitive( dto.getIsInsensitive() );
        documentsTagEntity.setIsInboxTag( dto.getIsInboxTag() );
        documentsTagEntity.setColor( map( dto.getColor() ) );

        return documentsTagEntity;
    }
}
