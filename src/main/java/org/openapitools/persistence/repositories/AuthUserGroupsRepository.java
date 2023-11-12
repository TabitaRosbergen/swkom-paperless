package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.AuthUserGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserGroupsRepository extends JpaRepository<AuthUserGroupsEntity, Integer> {
}
