package org.openapitools.services;

import co.elastic.clients.elasticsearch._types.Result;
import org.openapitools.model.Document;

import java.io.IOException;
import java.util.Optional;

public interface SearchIndexService {
    Result indexDocument(Document document) throws IOException;

    Optional<Document> getDocumentById(int id);

    boolean deleteDocumentById(int id);
}
