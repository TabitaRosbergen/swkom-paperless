package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.AuthGroupPermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthGroupPermissionsRepository extends JpaRepository<AuthGroupPermissionsEntity, Integer> {
}
