package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.DocumentsLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsLogRepository extends JpaRepository<DocumentsLogEntity, Integer> {
}
