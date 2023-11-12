package org.openapitools.persistence.entities;

import javax.persistence.*;

@Entity
public class AuthGroupPermissionsEntity {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private AuthGroupEntity group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private AuthPermissionEntity permission;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public AuthGroupEntity getGroup() {
        return group;
    }

    public void setGroup(final AuthGroupEntity group) {
        this.group = group;
    }

    public AuthPermissionEntity getPermission() {
        return permission;
    }

    public void setPermission(final AuthPermissionEntity permission) {
        this.permission = permission;
    }

}
