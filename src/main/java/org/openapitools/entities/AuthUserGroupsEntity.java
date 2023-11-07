package org.openapitools.entities;

import javax.persistence.*;


@Entity
public class AuthUserGroupsEntity {

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
    @JoinColumn(name = "group_id", nullable = false)
    private AuthGroupEntity group;

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

    public AuthGroupEntity getGroup() {
        return group;
    }

    public void setGroup(final AuthGroupEntity group) {
        this.group = group;
    }

}
