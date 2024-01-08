package org.openapitools.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;
import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentsDocumentEntity {

    @javax.persistence.Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )

    @Id
    private Integer id;

    @Column(nullable = false, length = 128)
    private String title;

    @MultiField(mainField = @Field(type = Text, fielddata = true), otherFields = { @InnerField(suffix = "verbatim", type = Keyword) })
    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private OffsetDateTime created;

    @Column(nullable = false)
    private OffsetDateTime modified;

    @Column(nullable = false, length = 32)
    private String checksum;

    @Column(nullable = false)
    private OffsetDateTime added;

    @Column(nullable = false, length = 11)
    private String storageType;

    @Column(length = 1024)
    private String filename;

    @Column
    private Integer archiveSerialNumber;

    @Column(nullable = false, length = 256)
    private String mimeType;

    @Column(length = 32)
    private String archiveChecksum;

    @Column(length = 1024)
    private String archiveFilename;

    @Column(length = 1024)
    private String originalFilename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correspondent_id")
    private DocumentsCorrespondentEntity correspondent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentsDocumenttypeEntity documentType;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "storage_path_id")
    private DocumentsStoragepathEntity storagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUserEntity owner;

    @OneToMany(mappedBy = "document")
    private Set<DocumentsNoteEntity> documentDocumentsNoteEntities;

    @OneToMany(mappedBy = "document")
    private Set<DocumentsDocumentTagsEntity> documentDocumentsDocumentTagsEntities;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(final OffsetDateTime created) {
        this.created = created;
    }

    public OffsetDateTime getModified() {
        return modified;
    }

    public void setModified(final OffsetDateTime modified) {
        this.modified = modified;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(final String checksum) {
        this.checksum = checksum;
    }

    public OffsetDateTime getAdded() {
        return added;
    }

    public void setAdded(final OffsetDateTime added) {
        this.added = added;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(final String storageType) {
        this.storageType = storageType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public Integer getArchiveSerialNumber() {
        return archiveSerialNumber;
    }

    public void setArchiveSerialNumber(final Integer archiveSerialNumber) {
        this.archiveSerialNumber = archiveSerialNumber;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    public String getArchiveChecksum() {
        return archiveChecksum;
    }

    public void setArchiveChecksum(final String archiveChecksum) {
        this.archiveChecksum = archiveChecksum;
    }

    public String getArchiveFilename() {
        return archiveFilename;
    }

    public void setArchiveFilename(final String archiveFilename) {
        this.archiveFilename = archiveFilename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(final String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public DocumentsCorrespondentEntity getCorrespondent() {
        return correspondent;
    }

    public void setCorrespondent(final DocumentsCorrespondentEntity correspondent) {
        this.correspondent = correspondent;
    }

    public DocumentsDocumenttypeEntity getDocumentType() {
        return documentType;
    }

    public void setDocumentType(final DocumentsDocumenttypeEntity documentType) {
        this.documentType = documentType;
    }

    public DocumentsStoragepathEntity getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(final DocumentsStoragepathEntity storagePath) {
        this.storagePath = storagePath;
    }

    public AuthUserEntity getOwner() {
        return owner;
    }

    public void setOwner(final AuthUserEntity owner) {
        this.owner = owner;
    }

    public Set<DocumentsNoteEntity> getDocumentDocumentsNotes() {
        return documentDocumentsNoteEntities;
    }

    public void setDocumentDocumentsNotes(final Set<DocumentsNoteEntity> documentDocumentsNoteEntities) {
        this.documentDocumentsNoteEntities = documentDocumentsNoteEntities;
    }

    public Set<DocumentsDocumentTagsEntity> getDocumentDocumentsDocumentTags() {
        return documentDocumentsDocumentTagsEntities;
    }

    public void setDocumentDocumentsDocumentTags(
            final Set<DocumentsDocumentTagsEntity> documentDocumentsDocumentTagsEntities) {
        this.documentDocumentsDocumentTagsEntities = documentDocumentsDocumentTagsEntities;
    }

}
