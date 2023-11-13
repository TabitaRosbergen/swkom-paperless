package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
public class DocumentsDocumenttypeEntity {

    @Id
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
    private Integer id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 256)
    private String match;

    @Column(nullable = false)
    private Integer matchingAlgorithm;

    @Column(nullable = false)
    private Boolean isInsensitive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUserEntity owner;

    @OneToMany(mappedBy = "documentType")
    private Set<DocumentsDocumentEntity> documentTypeDocumentsDocumentEntities;

    @OneToMany(mappedBy = "assignDocumentType")
    private Set<PaperlessMailMailruleEntity> assignDocumentTypePaperlessMailMailruleEntities;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(final String match) {
        this.match = match;
    }

    public Integer getMatchingAlgorithm() {
        return matchingAlgorithm;
    }

    public void setMatchingAlgorithm(final Integer matchingAlgorithm) {
        this.matchingAlgorithm = matchingAlgorithm;
    }

    public Boolean getIsInsensitive() {
        return isInsensitive;
    }

    public void setIsInsensitive(final Boolean isInsensitive) {
        this.isInsensitive = isInsensitive;
    }

    public AuthUserEntity getOwner() {
        return owner;
    }

    public void setOwner(final AuthUserEntity owner) {
        this.owner = owner;
    }

    public Set<DocumentsDocumentEntity> getDocumentTypeDocumentsDocuments() {
        return documentTypeDocumentsDocumentEntities;
    }

    public void setDocumentTypeDocumentsDocuments(
            final Set<DocumentsDocumentEntity> documentTypeDocumentsDocumentEntities) {
        this.documentTypeDocumentsDocumentEntities = documentTypeDocumentsDocumentEntities;
    }

    public Set<PaperlessMailMailruleEntity> getAssignDocumentTypePaperlessMailMailrules() {
        return assignDocumentTypePaperlessMailMailruleEntities;
    }

    public void setAssignDocumentTypePaperlessMailMailrules(
            final Set<PaperlessMailMailruleEntity> assignDocumentTypePaperlessMailMailruleEntities) {
        this.assignDocumentTypePaperlessMailMailruleEntities = assignDocumentTypePaperlessMailMailruleEntities;
    }

}
