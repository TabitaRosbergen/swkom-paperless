package org.openapitools.repositories;

import org.openapitools.entities.DocumentsCorrespondentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface DocumentsCorrespondentRepository extends JpaRepository<DocumentsCorrespondentEntity, Integer> {
}
