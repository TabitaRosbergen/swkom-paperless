package org.openapitools.services.impl;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;


@Document(indexName="files")
public class DocumentDTO {

    @Id
    public String id;

    public String content;

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
