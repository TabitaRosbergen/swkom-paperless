package org.openapitools.repositories;

import org.openapitools.entities.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserRepository extends JpaRepository<AuthUserEntity, Integer> {
}
