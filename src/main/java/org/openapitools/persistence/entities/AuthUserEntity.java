package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;
import java.time.OffsetDateTime;


@Entity
public class AuthUserEntity {

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
    private String password;

    @Column
    private OffsetDateTime lastLogin;

    @Column(nullable = false)
    private Boolean isSuperuser;

    @Column(nullable = false, length = 150)
    private String username;

    @Column(nullable = false, length = 150)
    private String firstName;

    @Column(nullable = false, length = 150)
    private String lastName;

    @Column(nullable = false, length = 254)
    private String email;

    @Column(nullable = false)
    private Boolean isStaff;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private OffsetDateTime dateJoined;

    @OneToMany(mappedBy = "user")
    private Set<AuthUserGroupsEntity> userAuthUserGroupsEntities;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsCorrespondentEntity> ownerDocumentsCorrespondentEntities;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsDocumenttypeEntity> ownerDocumentsDocumenttypeEntities;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsStoragepathEntity> ownerDocumentsStoragepathEntities;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsTagEntity> ownerDocumentsTagEntities;

    @OneToMany(mappedBy = "user")
    private Set<DocumentsUisettingsEntity> userDocumentsUisettingsEntities;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsSavedviewEntity> ownerDocumentsSavedviewEntities;

    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailMailaccountEntity> ownerPaperlessMailMailaccountEntities;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsDocumentEntity> ownerDocumentsDocumentEntities;

    @OneToMany(mappedBy = "user")
    private Set<DocumentsNoteEntity> userDocumentsNoteEntities;

    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailMailruleEntity> ownerPaperlessMailMailruleEntities;

    @OneToMany(mappedBy = "user")
    private Set<AuthUserUserPermissionsEntity> userAuthUserUserPermissionsEntities;

    @OneToMany(mappedBy = "user")
    private Set<AuthtokenTokenEntity> userAuthtokenTokenEntities;

    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailProcessedmailEntity> ownerPaperlessMailProcessedmailEntities;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public OffsetDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(final OffsetDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(final Boolean isSuperuser) {
        this.isSuperuser = isSuperuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Boolean getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(final Boolean isStaff) {
        this.isStaff = isStaff;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    public OffsetDateTime getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(final OffsetDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Set<AuthUserGroupsEntity> getUserAuthUserGroupses() {
        return userAuthUserGroupsEntities;
    }

    public void setUserAuthUserGroupses(final Set<AuthUserGroupsEntity> userAuthUserGroupsEntities) {
        this.userAuthUserGroupsEntities = userAuthUserGroupsEntities;
    }

    public Set<DocumentsCorrespondentEntity> getOwnerDocumentsCorrespondents() {
        return ownerDocumentsCorrespondentEntities;
    }

    public void setOwnerDocumentsCorrespondents(
            final Set<DocumentsCorrespondentEntity> ownerDocumentsCorrespondentEntities) {
        this.ownerDocumentsCorrespondentEntities = ownerDocumentsCorrespondentEntities;
    }

    public Set<DocumentsDocumenttypeEntity> getOwnerDocumentsDocumenttypes() {
        return ownerDocumentsDocumenttypeEntities;
    }

    public void setOwnerDocumentsDocumenttypes(
            final Set<DocumentsDocumenttypeEntity> ownerDocumentsDocumenttypeEntities) {
        this.ownerDocumentsDocumenttypeEntities = ownerDocumentsDocumenttypeEntities;
    }

    public Set<DocumentsStoragepathEntity> getOwnerDocumentsStoragepaths() {
        return ownerDocumentsStoragepathEntities;
    }

    public void setOwnerDocumentsStoragepaths(
            final Set<DocumentsStoragepathEntity> ownerDocumentsStoragepathEntities) {
        this.ownerDocumentsStoragepathEntities = ownerDocumentsStoragepathEntities;
    }

    public Set<DocumentsTagEntity> getOwnerDocumentsTags() {
        return ownerDocumentsTagEntities;
    }

    public void setOwnerDocumentsTags(final Set<DocumentsTagEntity> ownerDocumentsTagEntities) {
        this.ownerDocumentsTagEntities = ownerDocumentsTagEntities;
    }

    public Set<DocumentsUisettingsEntity> getUserDocumentsUisettingses() {
        return userDocumentsUisettingsEntities;
    }

    public void setUserDocumentsUisettingses(
            final Set<DocumentsUisettingsEntity> userDocumentsUisettingsEntities) {
        this.userDocumentsUisettingsEntities = userDocumentsUisettingsEntities;
    }

    public Set<DocumentsSavedviewEntity> getOwnerDocumentsSavedviews() {
        return ownerDocumentsSavedviewEntities;
    }

    public void setOwnerDocumentsSavedviews(
            final Set<DocumentsSavedviewEntity> ownerDocumentsSavedviewEntities) {
        this.ownerDocumentsSavedviewEntities = ownerDocumentsSavedviewEntities;
    }

    public Set<PaperlessMailMailaccountEntity> getOwnerPaperlessMailMailaccounts() {
        return ownerPaperlessMailMailaccountEntities;
    }

    public void setOwnerPaperlessMailMailaccounts(
            final Set<PaperlessMailMailaccountEntity> ownerPaperlessMailMailaccountEntities) {
        this.ownerPaperlessMailMailaccountEntities = ownerPaperlessMailMailaccountEntities;
    }

    public Set<DocumentsDocumentEntity> getOwnerDocumentsDocuments() {
        return ownerDocumentsDocumentEntities;
    }

    public void setOwnerDocumentsDocuments(final Set<DocumentsDocumentEntity> ownerDocumentsDocumentEntities) {
        this.ownerDocumentsDocumentEntities = ownerDocumentsDocumentEntities;
    }

    public Set<DocumentsNoteEntity> getUserDocumentsNotes() {
        return userDocumentsNoteEntities;
    }

    public void setUserDocumentsNotes(final Set<DocumentsNoteEntity> userDocumentsNoteEntities) {
        this.userDocumentsNoteEntities = userDocumentsNoteEntities;
    }

    public Set<PaperlessMailMailruleEntity> getOwnerPaperlessMailMailrules() {
        return ownerPaperlessMailMailruleEntities;
    }

    public void setOwnerPaperlessMailMailrules(
            final Set<PaperlessMailMailruleEntity> ownerPaperlessMailMailruleEntities) {
        this.ownerPaperlessMailMailruleEntities = ownerPaperlessMailMailruleEntities;
    }

    public Set<AuthUserUserPermissionsEntity> getUserAuthUserUserPermissionses() {
        return userAuthUserUserPermissionsEntities;
    }

    public void setUserAuthUserUserPermissionses(
            final Set<AuthUserUserPermissionsEntity> userAuthUserUserPermissionsEntities) {
        this.userAuthUserUserPermissionsEntities = userAuthUserUserPermissionsEntities;
    }

    public Set<AuthtokenTokenEntity> getUserAuthtokenTokens() {
        return userAuthtokenTokenEntities;
    }

    public void setUserAuthtokenTokens(final Set<AuthtokenTokenEntity> userAuthtokenTokenEntities) {
        this.userAuthtokenTokenEntities = userAuthtokenTokenEntities;
    }

    public Set<PaperlessMailProcessedmailEntity> getOwnerPaperlessMailProcessedmails() {
        return ownerPaperlessMailProcessedmailEntities;
    }

    public void setOwnerPaperlessMailProcessedmails(
            final Set<PaperlessMailProcessedmailEntity> ownerPaperlessMailProcessedmailEntities) {
        this.ownerPaperlessMailProcessedmailEntities = ownerPaperlessMailProcessedmailEntities;
    }

}
