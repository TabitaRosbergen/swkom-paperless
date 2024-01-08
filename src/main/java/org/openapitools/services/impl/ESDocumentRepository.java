//
//import org.openapitools.configuration.ElasticSearchConfig;
//import org.openapitools.model.Document;
//import org.openapitools.services.requestservices.SearchIndexService;
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch._types.Result;
//import co.elastic.clients.elasticsearch.core.DeleteResponse;
//import co.elastic.clients.elasticsearch.core.GetResponse;
//import co.elastic.clients.elasticsearch.core.IndexResponse;
//import org.openapitools.services.impl.MessageService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@Component
//public class ElasticSearchService implements SearchIndexService {
//    private final ElasticsearchClient esClient;
//    private final Logger log = LoggerFactory.getLogger(MessageService.class);
//
//    @Autowired
//    public ElasticSearchService(ElasticsearchClient esClient) throws IOException {
//        this.esClient = esClient;
//
//        if (!esClient.indices().exists(
//                i -> i.index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
//        ).value()) {
//            esClient.indices().create(c -> c
//                    .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
//            );
//        }
//    }
//
//    @Override
//    public Result indexDocument(Document document) throws IOException {
//        // do indexing with ElasticSearch
//        IndexResponse response = esClient.index(i -> i
//                .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
//                .id(document.getId().toString())
//                .document(document)
//        );
//        String logMsg = "Indexed document " + document.getId() + ": result=" + response.result() + ", index=" + response.index();
//        if ( response.result()!=Result.Created && response.result()!=Result.Updated )
//            log.error("Failed to " + logMsg);
//        else
//            log.info(logMsg);
//        return response.result();
//    }
//
//    @Override
//    public Optional<Document> getDocumentById(int id) {
//        try {
//            GetResponse<Document> response = esClient.get(g -> g
//                            .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
//                            .id(String.valueOf(id)),
//                    Document.class
//            );
//            return (response.found() && response.source()!=null) ? Optional.of(response.source()) : Optional.empty();
//        } catch (IOException e) {
//            log.error("Failed to get document id=" + id + " from elasticsearch: " + e);
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public boolean deleteDocumentById(int id) {
//        DeleteResponse result = null;
//        try {
//            result = esClient.delete(d -> d.index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME).id(String.valueOf(id)));
//        } catch (IOException e) {
//            log.warn("Failed to delete document id=" + id + " from elasticsearch: " + e);
//        }
//        if ( result==null )
//            return false;
//        if (result.result() != Result.Deleted )
//            log.warn(result.toString());
//        return result.result()==Result.Deleted;
//    }
//
//}

package org.openapitools.services.impl;//package org.openapitools.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ESDocumentRepository extends ElasticsearchRepository<documentDTO, Integer> {

    //@Query("{\"bool\": {\"must\": [{\"match\": {\"DocumentsDocumentEntity.content\": \"?0\"}}]}}")
    Page<documentDTO> findByContentContains(String content, Pageable pageable);

    /*
    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    Page<DocumentsDocumentEntity> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);

    @Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"term\": {\"tags\": \"?0\" }}}}")
    Page<DocumentsDocumentEntity> findByFilteredTagQuery(String tag, Pageable pageable);

    @Query("{\"bool\": {\"must\": {\"match\": {\"authors.name\": \"?0\"}}, \"filter\": {\"term\": {\"tags\": \"?1\" }}}}")
    Page<DocumentsDocumentEntity> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable);
     */
}
