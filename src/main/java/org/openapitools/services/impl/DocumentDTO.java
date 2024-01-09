package org.openapitools.services.impl;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.time.OffsetDateTime;


@Document(indexName="files")
public class DocumentDTO {

    @Id
    public String id;

    @Field(type = FieldType.Text)
    public String content;

    @Field(type = FieldType.Text)
    public String title;

    @Field(type = FieldType.Date)
    public OffsetDateTime created;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public OffsetDateTime getCreated() {
        return created;
    }
}
