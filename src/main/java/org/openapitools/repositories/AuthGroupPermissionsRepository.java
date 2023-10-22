package org.openapitools.repositories;

import org.openapitools.entities.AuthGroupPermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthGroupPermissionsRepository extends JpaRepository<AuthGroupPermissionsEntity, Integer> {
}
