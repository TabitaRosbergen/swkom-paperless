package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.DocumentsTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsTagRepository extends JpaRepository<DocumentsTagEntity, Integer> {
}
