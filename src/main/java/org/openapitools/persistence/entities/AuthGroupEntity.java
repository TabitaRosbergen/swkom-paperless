package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
public class AuthGroupEntity {

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

    @Column(nullable = false, length = 150)
    private String name;

    @OneToMany(mappedBy = "group")
    private Set<AuthUserGroupsEntity> groupAuthUserGroupsEntities;

    @OneToMany(mappedBy = "group")
    private Set<AuthGroupPermissionsEntity> groupAuthGroupPermissionsEntities;

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

    public Set<AuthUserGroupsEntity> getGroupAuthUserGroupses() {
        return groupAuthUserGroupsEntities;
    }

    public void setGroupAuthUserGroupses(final Set<AuthUserGroupsEntity> groupAuthUserGroupsEntities) {
        this.groupAuthUserGroupsEntities = groupAuthUserGroupsEntities;
    }

    public Set<AuthGroupPermissionsEntity> getGroupAuthGroupPermissionses() {
        return groupAuthGroupPermissionsEntities;
    }

    public void setGroupAuthGroupPermissionses(
            final Set<AuthGroupPermissionsEntity> groupAuthGroupPermissionsEntities) {
        this.groupAuthGroupPermissionsEntities = groupAuthGroupPermissionsEntities;
    }

}
