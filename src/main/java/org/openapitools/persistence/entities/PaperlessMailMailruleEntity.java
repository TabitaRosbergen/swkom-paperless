package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
public class PaperlessMailMailruleEntity {

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

    @Column(nullable = false, length = 256)
    private String name;

    @Column(nullable = false, length = 256)
    private String folder;

    @Column(length = 256)
    private String filterFrom;

    @Column(length = 256)
    private String filterSubject;

    @Column(length = 256)
    private String filterBody;

    @Column(nullable = false)
    private Integer maximumAge;

    @Column(nullable = false)
    private Integer action;

    @Column(length = 256)
    private String actionParameter;

    @Column(nullable = false)
    private Integer assignTitleFrom;

    @Column(nullable = false)
    private Integer assignCorrespondentFrom;

    @Column(nullable = false, name = "\"order\"")
    private Integer order;

    @Column(nullable = false)
    private Integer attachmentType;

    @Column(length = 256)
    private String filterAttachmentFilename;

    @Column(nullable = false)
    private Integer consumptionScope;

    @Column(length = 256)
    private String filterTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private PaperlessMailMailaccountEntity account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assign_correspondent_id")
    private DocumentsCorrespondentEntity assignCorrespondent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assign_document_type_id")
    private DocumentsDocumenttypeEntity assignDocumentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUserEntity owner;

    @OneToMany(mappedBy = "rule")
    private Set<PaperlessMailProcessedmailEntity> rulePaperlessMailProcessedmailEntities;

    @OneToMany(mappedBy = "mailrule")
    private Set<PaperlessMailMailruleAssignTagsEntity> mailrulePaperlessMailMailruleAssignTagsEntities;

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

    public String getFolder() {
        return folder;
    }

    public void setFolder(final String folder) {
        this.folder = folder;
    }

    public String getFilterFrom() {
        return filterFrom;
    }

    public void setFilterFrom(final String filterFrom) {
        this.filterFrom = filterFrom;
    }

    public String getFilterSubject() {
        return filterSubject;
    }

    public void setFilterSubject(final String filterSubject) {
        this.filterSubject = filterSubject;
    }

    public String getFilterBody() {
        return filterBody;
    }

    public void setFilterBody(final String filterBody) {
        this.filterBody = filterBody;
    }

    public Integer getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(final Integer maximumAge) {
        this.maximumAge = maximumAge;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(final Integer action) {
        this.action = action;
    }

    public String getActionParameter() {
        return actionParameter;
    }

    public void setActionParameter(final String actionParameter) {
        this.actionParameter = actionParameter;
    }

    public Integer getAssignTitleFrom() {
        return assignTitleFrom;
    }

    public void setAssignTitleFrom(final Integer assignTitleFrom) {
        this.assignTitleFrom = assignTitleFrom;
    }

    public Integer getAssignCorrespondentFrom() {
        return assignCorrespondentFrom;
    }

    public void setAssignCorrespondentFrom(final Integer assignCorrespondentFrom) {
        this.assignCorrespondentFrom = assignCorrespondentFrom;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(final Integer order) {
        this.order = order;
    }

    public Integer getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(final Integer attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getFilterAttachmentFilename() {
        return filterAttachmentFilename;
    }

    public void setFilterAttachmentFilename(final String filterAttachmentFilename) {
        this.filterAttachmentFilename = filterAttachmentFilename;
    }

    public Integer getConsumptionScope() {
        return consumptionScope;
    }

    public void setConsumptionScope(final Integer consumptionScope) {
        this.consumptionScope = consumptionScope;
    }

    public String getFilterTo() {
        return filterTo;
    }

    public void setFilterTo(final String filterTo) {
        this.filterTo = filterTo;
    }

    public PaperlessMailMailaccountEntity getAccount() {
        return account;
    }

    public void setAccount(final PaperlessMailMailaccountEntity account) {
        this.account = account;
    }

    public DocumentsCorrespondentEntity getAssignCorrespondent() {
        return assignCorrespondent;
    }

    public void setAssignCorrespondent(final DocumentsCorrespondentEntity assignCorrespondent) {
        this.assignCorrespondent = assignCorrespondent;
    }

    public DocumentsDocumenttypeEntity getAssignDocumentType() {
        return assignDocumentType;
    }

    public void setAssignDocumentType(final DocumentsDocumenttypeEntity assignDocumentType) {
        this.assignDocumentType = assignDocumentType;
    }

    public AuthUserEntity getOwner() {
        return owner;
    }

    public void setOwner(final AuthUserEntity owner) {
        this.owner = owner;
    }

    public Set<PaperlessMailProcessedmailEntity> getRulePaperlessMailProcessedmails() {
        return rulePaperlessMailProcessedmailEntities;
    }

    public void setRulePaperlessMailProcessedmails(
            final Set<PaperlessMailProcessedmailEntity> rulePaperlessMailProcessedmailEntities) {
        this.rulePaperlessMailProcessedmailEntities = rulePaperlessMailProcessedmailEntities;
    }

    public Set<PaperlessMailMailruleAssignTagsEntity> getMailrulePaperlessMailMailruleAssignTagses() {
        return mailrulePaperlessMailMailruleAssignTagsEntities;
    }

    public void setMailrulePaperlessMailMailruleAssignTagses(
            final Set<PaperlessMailMailruleAssignTagsEntity> mailrulePaperlessMailMailruleAssignTagsEntities) {
        this.mailrulePaperlessMailMailruleAssignTagsEntities = mailrulePaperlessMailMailruleAssignTagsEntities;
    }

}
