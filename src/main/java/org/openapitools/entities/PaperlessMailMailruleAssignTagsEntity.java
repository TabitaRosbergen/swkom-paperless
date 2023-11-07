package org.openapitools.entities;

import javax.persistence.*;

@Entity
public class PaperlessMailMailruleAssignTagsEntity {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mailrule_id", nullable = false)
    private PaperlessMailMailruleEntity mailrule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private DocumentsTagEntity tag;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public PaperlessMailMailruleEntity getMailrule() {
        return mailrule;
    }

    public void setMailrule(final PaperlessMailMailruleEntity mailrule) {
        this.mailrule = mailrule;
    }

    public DocumentsTagEntity getTag() {
        return tag;
    }

    public void setTag(final DocumentsTagEntity tag) {
        this.tag = tag;
    }

}
