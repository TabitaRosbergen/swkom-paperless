package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
public class DocumentsCorrespondentEntity {

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

    @OneToMany(mappedBy = "correspondent")
    private Set<DocumentsDocumentEntity> correspondentDocumentsDocumentEntities;

    @OneToMany(mappedBy = "assignCorrespondent")
    private Set<PaperlessMailMailruleEntity> assignCorrespondentPaperlessMailMailruleEntities;

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

    public Set<DocumentsDocumentEntity> getCorrespondentDocumentsDocuments() {
        return correspondentDocumentsDocumentEntities;
    }

    public void setCorrespondentDocumentsDocuments(
            final Set<DocumentsDocumentEntity> correspondentDocumentsDocumentEntities) {
        this.correspondentDocumentsDocumentEntities = correspondentDocumentsDocumentEntities;
    }

    public Set<PaperlessMailMailruleEntity> getAssignCorrespondentPaperlessMailMailrules() {
        return assignCorrespondentPaperlessMailMailruleEntities;
    }

    public void setAssignCorrespondentPaperlessMailMailrules(
            final Set<PaperlessMailMailruleEntity> assignCorrespondentPaperlessMailMailruleEntities) {
        this.assignCorrespondentPaperlessMailMailruleEntities = assignCorrespondentPaperlessMailMailruleEntities;
    }

}
