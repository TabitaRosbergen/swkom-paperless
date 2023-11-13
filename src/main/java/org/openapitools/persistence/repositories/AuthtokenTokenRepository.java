package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.AuthtokenTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthtokenTokenRepository extends JpaRepository<AuthtokenTokenEntity, Long> {
}
