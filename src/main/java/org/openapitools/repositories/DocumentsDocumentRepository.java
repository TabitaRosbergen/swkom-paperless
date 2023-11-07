package org.openapitools.repositories;

import org.openapitools.entities.DocumentsDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentsDocumentRepository extends JpaRepository<DocumentsDocumentEntity, Integer> {
}
