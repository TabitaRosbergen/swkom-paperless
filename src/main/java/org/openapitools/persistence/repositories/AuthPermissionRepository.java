package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.AuthPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthPermissionRepository extends JpaRepository<AuthPermissionEntity, Integer> {
}
