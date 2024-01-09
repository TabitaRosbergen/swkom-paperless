package org.openapitools.services.interfaces;//package org.openapitools.services.impl;

import org.openapitools.services.impl.DocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ESDocumentRepository extends ElasticsearchRepository<DocumentDTO, String> {
    @Query("{\"query_string\": {\"query\": \"?0\"}}")
    Page<DocumentDTO> findByContentContainsCustom(String content, Pageable pageable);

    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"content\" : {\"query\" : \"*?*\",\"analyze_wildcard\" : true}}}}}")
    List<DocumentDTO> findByContentContains(String content);
}