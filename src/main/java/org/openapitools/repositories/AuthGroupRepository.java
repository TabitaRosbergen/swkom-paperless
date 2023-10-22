package org.openapitools.repositories;

import org.openapitools.entities.AuthGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthGroupRepository extends JpaRepository<AuthGroupEntity, Integer> {
}
