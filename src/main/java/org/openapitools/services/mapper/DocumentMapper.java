package org.openapitools.services.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.model.Document;
import org.openapitools.persistence.entities.*;
import org.openapitools.persistence.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public abstract class DocumentMapper implements BaseMapper<DocumentsDocumentEntity, Document> {
    public static DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Autowired
    private DocumentsCorrespondentRepository correspondentRepository;
    @Autowired
    private DocumentsDocumenttypeRepository documentTypeRepository;
    @Autowired
    private DocumentsStoragepathRepository storagePathRepository;
    @Autowired
    private AuthUserRepository userRepository;
    @Autowired
    private DocumentsDocumentTagsRepository documentTagsRepository;

    @Mapping(target = "correspondent", source = "correspondent", qualifiedByName = "correspondentDto")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeDto")
    @Mapping(target = "storagePath", source = "storagePath", qualifiedByName = "storagePathDto")
    @Mapping(target = "documentDocumentsDocumentTags", source = "tags", qualifiedByName = "tagsDto")
    @Mapping(target = "archiveSerialNumber", source = "archiveSerialNumber", qualifiedByName = "archiveSerialNumberDto")
    abstract public DocumentsDocumentEntity dtoToEntity(Document dto);

    @Mapping(target = "correspondent", source = "correspondent", qualifiedByName = "correspondentEntity")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeEntity")
    @Mapping(target = "storagePath", source = "storagePath", qualifiedByName = "storagePathEntity")
    @Mapping(target = "tags", source = "documentDocumentsDocumentTags", qualifiedByName = "tagsEntity")
    @Mapping(target = "createdDate", source = "created", qualifiedByName = "createdToCreatedDate")
    abstract public Document entityToDto(DocumentsDocumentEntity entity);

    @Named("correspondentEntity")
    JsonNullable<Integer> map(DocumentsCorrespondentEntity correspondent) {
        return correspondent!=null ? JsonNullable.of(correspondent.getId()) : JsonNullable.undefined();
    }

    @Named("documentTypeEntity")
    JsonNullable<Integer> map(DocumentsDocumenttypeEntity documentType) {
        return documentType!=null ? JsonNullable.of(documentType.getId()) : JsonNullable.undefined();
    }

    @Named("storagePathEntity")
    JsonNullable<Integer> map(DocumentsStoragepathEntity storagePath) {
        return storagePath!=null ? JsonNullable.of(storagePath.getId()) : JsonNullable.undefined();
    }

    @Named("ownerEntity")
    JsonNullable<Integer> map(AuthUserEntity owner) {
        return owner!=null ? JsonNullable.of(owner.getId()) : JsonNullable.undefined();
    }

    @Named("tagsEntity")
    JsonNullable<List<Integer>> map(Set<DocumentsDocumentTagsEntity> tags) {
        return tags!=null ? JsonNullable.of( tags.stream().map( tag->(int)tag.getId() ).collect(Collectors.toList()) ) : JsonNullable.undefined();
    }

    // map created to createdDate (Date without the time)
    @Named("createdToCreatedDate")
    OffsetDateTime mapCreatedDate(OffsetDateTime value) {
        return value!=null ? value.withOffsetSameInstant(ZoneOffset.UTC).toLocalDate().atStartOfDay().atOffset(ZoneOffset.UTC) : null;
    }

    @Named("correspondentDto")
    DocumentsCorrespondentEntity mapCorrespondent(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;
        return correspondentRepository.findById(value.get()).orElse(null);
    }

    @Named("documentTypeDto")
    DocumentsDocumenttypeEntity mapDocumentType(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;
        return documentTypeRepository.findById(value.get()).orElse(null);
    }

    @Named("storagePathDto")
    DocumentsStoragepathEntity mapStoragePath(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;
        return storagePathRepository.findById(value.get()).orElse(null);
    }

    @Named("ownerDto")
    AuthUserEntity mapOwner(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;
        return userRepository.findById(value.get()).orElse(null);
    }

    @Named("tagsDto")
    Set<DocumentsDocumentTagsEntity> mapDocTag(JsonNullable<List<Integer>> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;
        return new HashSet<DocumentsDocumentTagsEntity>(documentTagsRepository.findAllById(value.get()));
    }

    @Named("archiveSerialNumberDto")
    Integer mapArchiveSerialNumber(JsonNullable<String> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;
        return Integer.parseInt(value.get());
    }

}
