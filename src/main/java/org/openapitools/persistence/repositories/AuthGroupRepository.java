package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.AuthGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthGroupRepository extends JpaRepository<AuthGroupEntity, Integer> {
}
