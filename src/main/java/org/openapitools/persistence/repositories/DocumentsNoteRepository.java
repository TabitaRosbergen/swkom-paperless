package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.DocumentsCorrespondentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsNoteRepository extends JpaRepository<DocumentsCorrespondentEntity, Integer> {
}
