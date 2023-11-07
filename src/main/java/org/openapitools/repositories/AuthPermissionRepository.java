package org.openapitools.repositories;

import org.openapitools.entities.AuthPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthPermissionRepository extends JpaRepository<AuthPermissionEntity, Integer> {
}
