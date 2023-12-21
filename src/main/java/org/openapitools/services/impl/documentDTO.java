package org.openapitools.services.impl;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;


@Document(indexName="files")
public class documentDTO {

    @Id
    public Integer id;

    public String content;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
