package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.DocumentsDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DocumentsDocumentRepository extends JpaRepository<DocumentsDocumentEntity, Integer> {
    List<DocumentsDocumentEntity> findByContentContains(String contentString);
}
