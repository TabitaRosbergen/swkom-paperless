//package org.openapitools.services.impl;//package org.openapitools.services.impl;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//
//public interface ESDocumentRepository extends ElasticsearchRepository<DocumentDTO, String> {
//
//    //@Query("{\"bool\": {\"must\": [{\"match\": {\"DocumentsDocumentEntity.content\": \"?0\"}}]}}")
////    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"content\" : {\"query\" : \"*?*\",\"analyze_wildcard\" : true}}}}}")
//    @Query("{\"query_string\": {\"query\": \"?0\"}}")
//    Page<DocumentDTO> findByContentContainsCustom(String content, Pageable pageable);
//
//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"content\" : {\"query\" : \"*?*\",\"analyze_wildcard\" : true}}}}}")
//    List<DocumentDTO> findByContentContains(String content);
//
//    /*
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
//    Page<DocumentsDocumentEntity> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
//
//    @Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"term\": {\"tags\": \"?0\" }}}}")
//    Page<DocumentsDocumentEntity> findByFilteredTagQuery(String tag, Pageable pageable);
//
//    @Query("{\"bool\": {\"must\": {\"match\": {\"authors.name\": \"?0\"}}, \"filter\": {\"term\": {\"tags\": \"?1\" }}}}")
//    Page<DocumentsDocumentEntity> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable);
//     */
//}