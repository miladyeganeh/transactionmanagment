package com.my.accountmanager.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "DOCUMENT_ITEM")
public class DocumentItemEntity extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "COMMENT")
    private String comment;

    @ManyToOne
    private DocumentEntity document;

    public DocumentItemEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }
}
