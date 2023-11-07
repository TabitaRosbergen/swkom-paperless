package org.openapitools.repositories;

import org.openapitools.entities.DocumentsCorrespondentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentsNoteRepository extends JpaRepository<DocumentsCorrespondentEntity, Integer> {
}
