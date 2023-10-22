package org.openapitools.repositories;

import org.openapitools.entities.AuthUserGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserGroupsRepository extends JpaRepository<AuthUserGroupsEntity, Integer> {
}
