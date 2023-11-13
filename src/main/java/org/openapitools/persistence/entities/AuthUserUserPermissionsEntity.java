package org.openapitools.persistence.entities;

import javax.persistence.*;

@Entity
public class AuthUserUserPermissionsEntity {

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
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private AuthPermissionEntity permission;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public AuthUserEntity getUser() {
        return user;
    }

    public void setUser(final AuthUserEntity user) {
        this.user = user;
    }

    public AuthPermissionEntity getPermission() {
        return permission;
    }

    public void setPermission(final AuthPermissionEntity permission) {
        this.permission = permission;
    }

}
