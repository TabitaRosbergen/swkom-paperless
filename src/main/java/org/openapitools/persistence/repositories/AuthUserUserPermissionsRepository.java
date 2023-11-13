package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.AuthUserUserPermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserUserPermissionsRepository extends JpaRepository<AuthUserUserPermissionsEntity, Integer> {
}
