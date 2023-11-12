package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserRepository extends JpaRepository<AuthUserEntity, Integer> {
}
