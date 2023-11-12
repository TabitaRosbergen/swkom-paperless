package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
public class AuthPermissionEntity {

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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String contentType;

    @Column(nullable = false, length = 100)
    private String codename;

    @OneToMany(mappedBy = "permission")
    private Set<AuthGroupPermissionsEntity> permissionAuthGroupPermissionsEntities;

    @OneToMany(mappedBy = "permission")
    private Set<AuthUserUserPermissionsEntity> permissionAuthUserUserPermissionsEntities;

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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(final String codename) {
        this.codename = codename;
    }

    public Set<AuthGroupPermissionsEntity> getPermissionAuthGroupPermissionses() {
        return permissionAuthGroupPermissionsEntities;
    }

    public void setPermissionAuthGroupPermissionses(
            final Set<AuthGroupPermissionsEntity> permissionAuthGroupPermissionsEntities) {
        this.permissionAuthGroupPermissionsEntities = permissionAuthGroupPermissionsEntities;
    }

    public Set<AuthUserUserPermissionsEntity> getPermissionAuthUserUserPermissionses() {
        return permissionAuthUserUserPermissionsEntities;
    }

    public void setPermissionAuthUserUserPermissionses(
            final Set<AuthUserUserPermissionsEntity> permissionAuthUserUserPermissionsEntities) {
        this.permissionAuthUserUserPermissionsEntities = permissionAuthUserUserPermissionsEntities;
    }

}
