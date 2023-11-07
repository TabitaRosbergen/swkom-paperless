package org.openapitools.repositories;

import org.openapitools.entities.DocumentsLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsLogRepository extends JpaRepository<DocumentsLogEntity, Integer> {
}
