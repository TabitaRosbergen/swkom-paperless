//package org.openapitools.services.impl;
//
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import javax.persistence.Id;
//
//@Document(indexName="files")
//public class DocumentDTO {
//
//    @Id
//    public String id;
//
//    @Field(type = FieldType.Text)
//    public String content;
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//}
