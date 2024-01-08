package org.openapitools.services.mapper;

import javax.annotation.Generated;
import org.openapitools.model.Document;
import org.openapitools.persistence.entities.DocumentsDocumentEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-08T18:04:26+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (SAP SE)"
)
@Component
public class DocumentMapperImpl extends DocumentMapper {

    @Override
    public DocumentsDocumentEntity dtoToEntity(Document dto) {
        if ( dto == null ) {
            return null;
        }

        DocumentsDocumentEntity documentsDocumentEntity = new DocumentsDocumentEntity();

        documentsDocumentEntity.setCorrespondent( mapCorrespondent( dto.getCorrespondent() ) );
        documentsDocumentEntity.setDocumentType( mapDocumentType( dto.getDocumentType() ) );
        documentsDocumentEntity.setStoragePath( mapStoragePath( dto.getStoragePath() ) );
        documentsDocumentEntity.setDocumentDocumentsDocumentTags( mapDocTag( dto.getTags() ) );
        documentsDocumentEntity.setArchiveSerialNumber( mapArchiveSerialNumber( dto.getArchiveSerialNumber() ) );
        documentsDocumentEntity.setId( dto.getId() );
        documentsDocumentEntity.setTitle( map( dto.getTitle() ) );
        documentsDocumentEntity.setContent( map( dto.getContent() ) );
        documentsDocumentEntity.setCreated( dto.getCreated() );
        documentsDocumentEntity.setModified( dto.getModified() );
        documentsDocumentEntity.setAdded( dto.getAdded() );

        return documentsDocumentEntity;
    }

    @Override
    public Document entityToDto(DocumentsDocumentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Document document = new Document();

        document.setCorrespondent( map( entity.getCorrespondent() ) );
        document.setDocumentType( map( entity.getDocumentType() ) );
        document.setStoragePath( map( entity.getStoragePath() ) );
        document.setTags( map( entity.getDocumentDocumentsDocumentTags() ) );
        document.setCreatedDate( mapCreatedDate( entity.getCreated() ) );
        document.setId( entity.getId() );
        document.setTitle( map( entity.getTitle() ) );
        document.setContent( map( entity.getContent() ) );
        document.setCreated( entity.getCreated() );
        document.setModified( entity.getModified() );
        document.setAdded( entity.getAdded() );
        if ( entity.getArchiveSerialNumber() != null ) {
            document.setArchiveSerialNumber( map( String.valueOf( entity.getArchiveSerialNumber() ) ) );
        }

        return document;
    }
}
